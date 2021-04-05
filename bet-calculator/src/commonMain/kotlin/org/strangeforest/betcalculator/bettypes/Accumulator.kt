package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

internal abstract class BaseAccumulator : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
       return sequenceOf(toCombination(items))
   }
}

internal abstract class BaseAccumulatorN(val itemCount: Int) : BaseAccumulator() {

   init {
      requireItemCountAtLeast(itemCount, 1)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

internal class AccumulatorN(itemCount: Int) : BaseAccumulatorN(itemCount) {

   override fun toString(): String = "Accumulator$itemCount"
}

internal class EachWayAccumulatorN(itemCount: Int, val unitCountFactor: Decimal = ONE) : BaseAccumulatorN(itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit {
      return BetUnit(unitStake, legs, this, rules.eachWay(), unitCountFactor)
   }
}

internal object Accumulator : BaseAccumulator()
internal object Single : BaseAccumulatorN(1)
internal object Double : BaseAccumulatorN(2)
internal object Treble : BaseAccumulatorN(3)
internal object Fourfold : BaseAccumulatorN(4)
internal object Fivefold : BaseAccumulatorN(5)
internal object Sixfold : BaseAccumulatorN(6)
internal object Sevenfold : BaseAccumulatorN(7)
internal object Eightfold : BaseAccumulatorN(8)
