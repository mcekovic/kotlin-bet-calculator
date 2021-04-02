package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.util.*

internal abstract class BaseFullCover(withSingles: Boolean = false) : BetType() {

   val fromCombinationSize = if (withSingles) 1 else 2

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, fromCombinationSize)
      return (fromCombinationSize..items.size).asSequence()
         .map { unitSize -> combinations(items, unitSize).map(::toCombination) }
         .flatten()
   }
}

internal abstract class BaseFullCoverN(val itemCount: Int, withSingles: Boolean = false) : BaseFullCover(withSingles) {

   init {
      requireItemCountAtLeast(itemCount, fromCombinationSize)
   }
}

internal class FullCoverN(itemCount: Int) : BaseFullCoverN(itemCount, false) {

   override fun toString(): String = "FullCover$itemCount"
}

internal class FullCoverWithSinglesN(itemCount: Int) : BaseFullCoverN(itemCount, true) {

   override fun toString(): String = "FullCoverWithSingles$itemCount"
}

internal object FullCover : BaseFullCover()
internal object Trixie : BaseFullCoverN(3)
internal object Yankee : BaseFullCoverN(4)
internal object Canadian : BaseFullCoverN(5)
internal object Heinz : BaseFullCoverN(6)
internal object SuperHeinz : BaseFullCoverN(7)
internal object Goliath : BaseFullCoverN(8)

internal object FullCoverWithSingles : BaseFullCover(true)
internal object Patent : BaseFullCoverN(3, true)
internal object Lucky15 : BaseFullCoverN(4, true)
internal object Lucky31 : BaseFullCoverN(5, true)
internal object Lucky63 : BaseFullCoverN(6, true)
internal object SuperHeinzWithSingles : BaseFullCoverN(7, true)
internal object GoliathWithSingles : BaseFullCoverN(8, true)
