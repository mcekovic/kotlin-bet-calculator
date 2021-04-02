package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*

internal open class StraightFullCover(val itemCount: Int) : BetType() {

   init {
      requireItemCountAtLeast(itemCount, 1)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return (1..itemCount).asSequence().map {
         unitSize -> items.asSequence().windowed(unitSize, 1, false, ::toCombination)
      }.flatten()
   }
}

internal object Fivespot : StraightFullCover(5)
internal object Pontoon : StraightFullCover(6)
internal object Magnificent7 : StraightFullCover(7)
