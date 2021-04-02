package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.util.*

internal open class PermedSubType(val subBetType: BetType, val subTypeItemCount: Int) : BetType() {

   init {
      require(subTypeItemCount > 0) { "subTypeItemCount must be positive" }
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, subTypeItemCount)
      return combinations(items, subTypeItemCount).map {
         subItems -> subBetType.combinations(subItems)
      }.flatten()
   }
}

internal object PermPatent : PermedSubType(Patent, 3)
internal object PermYankee : PermedSubType(Yankee, 4)
internal object PermRoundRobin : PermedSubType(RoundRobin, 3)
