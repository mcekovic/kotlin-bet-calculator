package org.strangeforest.betcalculator.bettypes

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.*

abstract class BaseAccumulator : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
       return sequenceOf(toCombination(items))
   }
}

abstract class BaseAccumulatorN(val itemCount: Int) : BaseAccumulator() {

   init {
      requireItemCountAtLeast(itemCount, 1)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

class AccumulatorN(itemCount: Int) : BaseAccumulatorN(itemCount) {

   override fun toString(): String = "Accumulator$itemCount"
}

class EachWayAccumulatorN(itemCount: Int, val unitCountFactor: BigDecimal = ONE) : BaseAccumulatorN(itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit {
      val unitRules = rules.eachWay()
      return BetUnit(unitStake, legs, this, unitRules, unitCountFactor)
   }
}

object Accumulator : BaseAccumulator()
object Single : BaseAccumulatorN(1)
object Double : BaseAccumulatorN(2)
object Treble : BaseAccumulatorN(3)
object Fourfold : BaseAccumulatorN(4)
object Fivefold : BaseAccumulatorN(5)
object Sixfold : BaseAccumulatorN(6)
object Sevenfold : BaseAccumulatorN(7)
object Eightfold : BaseAccumulatorN(8)