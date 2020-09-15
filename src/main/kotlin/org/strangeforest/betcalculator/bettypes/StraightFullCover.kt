package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.*

open class StraightFullCover(val itemCount: Int) : BetType() {

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

object Fivespot : StraightFullCover(5)
object Pontoon : StraightFullCover(6)
object Magnificent7 : StraightFullCover(7)
