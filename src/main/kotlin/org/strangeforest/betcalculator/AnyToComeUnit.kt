package org.strangeforest.betcalculator

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.bettypes.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayAmounts.Companion.EW_ZERO
import org.strangeforest.betcalculator.rules.EachWayFormula.*
import org.strangeforest.betcalculator.rules.EachWayType.*

class AnyToComeUnit(
   unitStake: BigDecimal, legs: List<BetLeg>, betType: BetType = Accumulator, rules: BetRules = BetRules.DEFAULT, unitCountFactor: BigDecimal = ONE,
   val stakeFactorCarriedForward: BigDecimal = ONE,
   val anyToComeOn: Int = 1
) : BetUnit(unitStake, legs, betType, rules, unitCountFactor) {

   override val cumulativePrice: BigDecimal by lazy {
      when (val eachWayType = rules.eachWayType) {
         WIN, PLACE ->
            legs.asSequence()
               .map { leg -> AnyToComeAmounts(leg.factoredPrice(eachWayType)) }
               .reduceIndexed { index, atc1, atc2 -> atc1.anyToCome(index) * atc2 }
               .total
         EACH_WAY ->
            legs.asSequence()
               .map { leg -> AnyToComeEachWayAmounts(EachWayAmounts(leg.factoredPrice(WIN), leg.factoredPrice(PLACE))) }
               .reduceIndexed { index, atc1, atc2 -> distributeEachWayAnyToCome(atc1.anyToCome(index)) * atc2}
               .total.total / EACH_WAY.unitCount
      }
   }

   private fun distributeEachWayAnyToCome(amounts: AnyToComeEachWayAmounts): AnyToComeEachWayAmounts = when (rules.eachWayAnyToComeFormula) {
      WIN_PLACE -> amounts
      EQUALLY_DIVIDED -> amounts.equallyDivided
      WIN_PRECEDENCE -> amounts.winPrecedence
   }

   private inner class AnyToComeAmounts(val runningOn: BigDecimal, val toReturn: BigDecimal = ZERO) {

      fun anyToCome(legIndex: Int): AnyToComeAmounts =
         if (legIndex == anyToComeOn && runningOn > stakeFactorCarriedForward) AnyToComeAmounts(stakeFactorCarriedForward, total - stakeFactorCarriedForward) else this

      operator fun times(other: AnyToComeAmounts) = AnyToComeAmounts(runningOn * other.runningOn, toReturn + other.toReturn)

      val total: BigDecimal
         get() = runningOn + toReturn
   }

   private inner class AnyToComeEachWayAmounts(val runningOn: EachWayAmounts, val toReturn: EachWayAmounts = EW_ZERO) {

      constructor(win: AnyToComeAmounts, place: AnyToComeAmounts) : this(EachWayAmounts(win.runningOn, place.runningOn), EachWayAmounts(win.toReturn, place.toReturn))

      fun anyToCome(legIndex: Int): AnyToComeEachWayAmounts = AnyToComeEachWayAmounts(win.anyToCome(legIndex), place.anyToCome(legIndex))

      operator fun times(other: AnyToComeEachWayAmounts) = AnyToComeEachWayAmounts(runningOn * other.runningOn, toReturn + other.toReturn)

      val win: AnyToComeAmounts
         get() = AnyToComeAmounts(runningOn.win, toReturn.win)

      val place: AnyToComeAmounts
         get() = AnyToComeAmounts(runningOn.place, toReturn.place)

      val total: EachWayAmounts
         get() = runningOn + toReturn

      val equallyDivided: AnyToComeEachWayAmounts
         get() = AnyToComeEachWayAmounts(runningOn.equallyDivided, toReturn)

      val winPrecedence: AnyToComeEachWayAmounts
         get() = AnyToComeEachWayAmounts(runningOn.winPrecedence(stakeFactorCarriedForward), toReturn)
   }
}
