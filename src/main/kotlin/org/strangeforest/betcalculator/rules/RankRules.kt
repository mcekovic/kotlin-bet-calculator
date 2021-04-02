package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.util.*

internal open class RankRules : LegRules<Rank> {

   override fun resultedStatus(leg: BetLeg, result: Rank): LegStatus =
      if (result != Rank.NON_RUNNER)
         LegStatus.resulted(getWinFactor(leg, result), ZERO)
      else
         LegStatus.VOID

   protected open fun getWinFactor(leg: BetLeg, rank: Rank): Decimal =
      if (rank.isWon())
         getDeadHeatFactor(leg, rank)
      else
         ZERO

   private fun getDeadHeatFactor(leg: BetLeg, rank: Rank): Decimal =
      if (isSingleWinner(leg))
         rank.deadHeatFactor()
      else
         ONE

   protected fun isSingleWinner(leg: BetLeg): Boolean = leg.irDescriptor.maxWinners == 1
}
