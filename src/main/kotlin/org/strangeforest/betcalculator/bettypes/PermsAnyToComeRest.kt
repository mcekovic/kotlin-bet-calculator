package org.strangeforest.betcalculator.bettypes

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.*

abstract class PermsAnyToComeRest(val stakeFactorCarriedForward: BigDecimal, val leftCombinationSize: Int) : BetType() {

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

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward, leftCombinationSize)
}

abstract class PermsAnyToComeRestN(stakeFactorCarriedForward: BigDecimal, leftUnitSize: Int, val itemCount: Int) : PermsAnyToComeRest(stakeFactorCarriedForward, leftUnitSize) {

   init {
      requireItemCountAtLeast(itemCount, 2)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

open class ReducedStakePermsAnyToComeRestN(stakeFactorCarriedForward: BigDecimal, leftUnitSize: Int, itemCount: Int, val unitCountFactor: BigDecimal) :
   PermsAnyToComeRestN(stakeFactorCarriedForward, leftUnitSize, itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, unitCountFactor, stakeFactorCarriedForward, leftCombinationSize)
}

class ReducedStakePermsAnyToComeSubTypeOfRestN(stakeFactorCarriedForward: BigDecimal, leftUnitSize: Int, itemCount: Int, unitCountFactor: BigDecimal, val subBetType: BetType) :
   ReducedStakePermsAnyToComeRestN(stakeFactorCarriedForward, leftUnitSize, itemCount, unitCountFactor) {

   override fun <T> unitSequence(items: List<T>): Sequence<Combination<T>> =
      combinations(items, leftCombinationSize).map {
         leftItems -> subBetType.combinations(items - leftItems)
            .map { right -> toCombination(leftItems + right.items) }
      }.flatten()
}

object Rounder : PermsAnyToComeRestN(ONE, 1, 3)
object Roundabout : PermsAnyToComeRestN(TWO, 1, 3)
object RoundTheClock : PermsAnyToComeRest(ONE, 1)
object Gyroscope : PermsAnyToComeRestN(ONE, 2, 5)
