package org.strangeforest.betcalculator.bettypes

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

abstract class UpAndDown(val stakeFactorCarriedForward: BigDecimal) : BasePerms(2, false) {

   init {
      validateStakeFactorCarriedForward(stakeFactorCarriedForward)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> = super.combinations(items)
      .map { combination -> sequenceOf(combination, combination.reversed()) }
      .flatten()

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward)
}

abstract class UpAndDownN(stakeFactorCarriedForward: BigDecimal, val itemCount: Int) : UpAndDown(stakeFactorCarriedForward) {

   init {
      requireItemCountAtLeast(itemCount, 2)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

class SingleStakesAboutN(itemCount: Int) : UpAndDownN(ONE, itemCount) {

   override fun toString(): String = "SingleStakesAbout$itemCount"
}

class DoubleStakesAboutN(itemCount: Int) : UpAndDownN(TWO, itemCount) {

   override fun toString(): String = "DoubleStakesAbout$itemCount"
}

object SingleStakesAbout : UpAndDown(ONE)
object DoubleStakesAbout : UpAndDown(TWO)

object RoundRobin : BaseCompoundBetType(Trixie, SingleStakesAboutN(3))
object Flag : BaseCompoundBetType(Yankee, SingleStakesAboutN(4))
object SuperFlag : BaseCompoundBetType(Canadian, SingleStakesAboutN(5))
object HeinzFlag : BaseCompoundBetType(Heinz, SingleStakesAboutN(6))
object SuperHeinzFlag : BaseCompoundBetType(SuperHeinz, SingleStakesAboutN(7))
object GoliathFlag : BaseCompoundBetType(Goliath, SingleStakesAboutN(8))
