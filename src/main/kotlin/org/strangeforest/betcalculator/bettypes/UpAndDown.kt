package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

internal abstract class UpAndDown(val stakeFactorCarriedForward: Decimal) : BasePerms(2, false) {

   init {
      validateStakeFactorCarriedForward(stakeFactorCarriedForward)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> = super.combinations(items)
      .map { combination -> sequenceOf(combination, combination.reversed()) }
      .flatten()

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward)
}

internal abstract class UpAndDownN(stakeFactorCarriedForward: Decimal, val itemCount: Int) : UpAndDown(stakeFactorCarriedForward) {

   init {
      requireItemCountAtLeast(itemCount, 2)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

internal class SingleStakesAboutN(itemCount: Int) : UpAndDownN(ONE, itemCount) {

   override fun toString(): String = "SingleStakesAbout$itemCount"
}

internal class DoubleStakesAboutN(itemCount: Int) : UpAndDownN(TWO, itemCount) {

   override fun toString(): String = "DoubleStakesAbout$itemCount"
}

internal object SingleStakesAbout : UpAndDown(ONE)
internal object DoubleStakesAbout : UpAndDown(TWO)

internal object RoundRobin : BaseCompoundBetType(Trixie, SingleStakesAboutN(3))
internal object Flag : BaseCompoundBetType(Yankee, SingleStakesAboutN(4))
internal object SuperFlag : BaseCompoundBetType(Canadian, SingleStakesAboutN(5))
internal object HeinzFlag : BaseCompoundBetType(Heinz, SingleStakesAboutN(6))
internal object SuperHeinzFlag : BaseCompoundBetType(SuperHeinz, SingleStakesAboutN(7))
internal object GoliathFlag : BaseCompoundBetType(Goliath, SingleStakesAboutN(8))
