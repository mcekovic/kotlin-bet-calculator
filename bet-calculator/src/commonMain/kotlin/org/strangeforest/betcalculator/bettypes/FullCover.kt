package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.util.*

abstract class BaseFullCover(withSingles: Boolean = false) : BetType() {

   val fromCombinationSize = if (withSingles) 1 else 2

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, fromCombinationSize)
      return (fromCombinationSize..items.size).asSequence()
         .map { unitSize -> combinations(items, unitSize).map(::toCombination) }
         .flatten()
   }
}

abstract class BaseFullCoverN(val itemCount: Int, withSingles: Boolean = false) : BaseFullCover(withSingles) {

   init {
      requireItemCountAtLeast(itemCount, fromCombinationSize)
   }
}

class FullCoverN(itemCount: Int) : BaseFullCoverN(itemCount, false) {

   override fun toString(): String = "FullCover$itemCount"
}

class FullCoverWithSinglesN(itemCount: Int) : BaseFullCoverN(itemCount, true) {

   override fun toString(): String = "FullCoverWithSingles$itemCount"
}

object FullCover : BaseFullCover()
object Trixie : BaseFullCoverN(3)
object Yankee : BaseFullCoverN(4)
object Canadian : BaseFullCoverN(5)
object Heinz : BaseFullCoverN(6)
object SuperHeinz : BaseFullCoverN(7)
object Goliath : BaseFullCoverN(8)

object FullCoverWithSingles : BaseFullCover(true)
object Patent : BaseFullCoverN(3, true)
object Lucky15 : BaseFullCoverN(4, true)
object Lucky31 : BaseFullCoverN(5, true)
object Lucky63 : BaseFullCoverN(6, true)
object SuperHeinzWithSingles : BaseFullCoverN(7, true)
object GoliathWithSingles : BaseFullCoverN(8, true)
