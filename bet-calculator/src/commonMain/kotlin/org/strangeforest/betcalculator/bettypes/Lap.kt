package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

open class Lap(val stakeFactorCarriedForward: Decimal) : BetType() {

   init {
      validateStakeFactorCarriedForward(stakeFactorCarriedForward)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, 2)
      return (items.asSequence() + sequenceOf(items.first()))
         .zipWithNext { item1, item2 -> toCombination(listOf(item1, item2)) }
   }

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward)
}

object SingleLap : Lap(ONE)
object DoubleLap : Lap(TWO)
