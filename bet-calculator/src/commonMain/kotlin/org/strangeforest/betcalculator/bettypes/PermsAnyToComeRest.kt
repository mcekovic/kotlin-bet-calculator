package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

internal abstract class PermsAnyToComeRest(val stakeFactorCarriedForward: Decimal, val leftCombinationSize: Int) : BetType() {

   init {
      validateStakeFactorCarriedForward(stakeFactorCarriedForward)
      require(leftCombinationSize > 0) { "leftCombinationSize must be positive" }
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, leftCombinationSize + 1)
      return unitSequence<T>(items)
   }

   open fun <T> unitSequence(items: List<T>): Sequence<Combination<T>> =
      combinations(items, leftCombinationSize).map { leftItems -> toCombination(leftItems + (items - leftItems)) }

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward, leftCombinationSize)
}

internal abstract class PermsAnyToComeRestN(stakeFactorCarriedForward: Decimal, leftUnitSize: Int, val itemCount: Int) : PermsAnyToComeRest(stakeFactorCarriedForward, leftUnitSize) {

   init {
      requireItemCountAtLeast(itemCount, 2)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

internal open class ReducedStakePermsAnyToComeRestN(stakeFactorCarriedForward: Decimal, leftUnitSize: Int, itemCount: Int, val unitCountFactor: Decimal) :
   PermsAnyToComeRestN(stakeFactorCarriedForward, leftUnitSize, itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, unitCountFactor, stakeFactorCarriedForward, leftCombinationSize)
}

internal class ReducedStakePermsAnyToComeSubTypeOfRestN(stakeFactorCarriedForward: Decimal, leftUnitSize: Int, itemCount: Int, unitCountFactor: Decimal, val subBetType: BetType) :
   ReducedStakePermsAnyToComeRestN(stakeFactorCarriedForward, leftUnitSize, itemCount, unitCountFactor) {

   override fun <T> unitSequence(items: List<T>): Sequence<Combination<T>> =
      combinations(items, leftCombinationSize).map {
         leftItems -> subBetType.combinations(items - leftItems)
            .map { right -> toCombination(leftItems + right.items) }
      }.flatten()
}

internal object Rounder : PermsAnyToComeRestN(ONE, 1, 3)
internal object Roundabout : PermsAnyToComeRestN(TWO, 1, 3)
internal object RoundTheClock : PermsAnyToComeRest(ONE, 1)
internal object Gyroscope : PermsAnyToComeRestN(ONE, 2, 5)
