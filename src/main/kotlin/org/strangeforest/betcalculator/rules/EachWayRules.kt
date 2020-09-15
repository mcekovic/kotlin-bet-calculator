package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.EachWayType.*

class EachWayRules(val eachWayType: EachWayType, val placeTerms: PlaceTerms) : RankRules() {

   init {
      require(eachWayType != EACH_WAY) { "eachWayType cannot be EACH_WAY" }
   }

   override val openStatus: LegStatus
      get() = if (eachWayType == WIN) super.openStatus else LegStatus.open(placeTerms.reductionFactor)

   override fun resultedStatus(leg: BetLeg, result: Rank): LegStatus =
      when (eachWayType) {
         WIN -> if (result == Rank.NON_RUNNER)
                   LegStatus.VOID
                else
                   LegStatus.resulted(getWinFactor(leg, result), ZERO)
         PLACE -> if (result == Rank.NON_RUNNER || placeTerms.winOnly)
                     LegStatus.void(placeTerms.reductionFactor)
                  else
                     LegStatus.resulted(getPlaceFactor(leg, result), ZERO, placeTerms.reductionFactor)
         EACH_WAY -> throw IllegalStateException()
      }

   override fun getWinFactor(leg: BetLeg, rank: Rank): Decimal =
      if (placeTerms.placeOnly && rank.isPlaced(placeTerms))
         getDeadHeatFactor(leg, rank, placeTerms)
      else
         super.getWinFactor(leg, rank)

   private fun getPlaceFactor(leg: BetLeg, rank: Rank): Decimal =
      if (rank.isPlaced(placeTerms))
         getDeadHeatFactor(leg, rank, placeTerms)
      else
         ZERO

   private fun getDeadHeatFactor(leg: BetLeg, rank: Rank, placeTerms: PlaceTerms): Decimal =
      if (isSingleWinner(leg)) rank.deadHeatFactor(placeTerms) else ONE
}
