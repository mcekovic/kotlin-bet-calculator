package org.strangeforest.betcalculator.bettypes

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.*

open class Lap(val stakeFactorCarriedForward: BigDecimal) : BetType() {

   init {
      validateStakeFactorCarriedForward(stakeFactorCarriedForward)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, 2)
      return (items.asSequence() + sequenceOf(items.first()))
         .zipWithNext { item1, item2 -> toCombination(listOf(item1, item2)) }
   }

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      AnyToComeUnit(unitStake, legs, this, rules, ONE, stakeFactorCarriedForward)
}

object SingleLap : Lap(ONE)
object DoubleLap : Lap(TWO)
