package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.util.*

internal abstract class BaseVariablePerms(val fromCombinationSize: Int, val toCombinationSize: Int, canSkipUnits: Boolean = true) : BetType(canSkipUnits) {

   init {
      require(fromCombinationSize > 0) { "fromCombinationSize must be positive" }
      require(toCombinationSize >= fromCombinationSize) { "toCombinationSize must be greater than or equal to fromCombinationSize" }
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, toCombinationSize)
      return (fromCombinationSize..toCombinationSize).asSequence()
         .map { unitSize -> combinations(items, unitSize).map(::toCombination) }
         .flatten()
   }
}

internal class VariablePerms(minUnitSize: Int, maxUnitSize: Int) : BaseVariablePerms(minUnitSize, maxUnitSize) {

   override fun toString(): String = "Perms$fromCombinationSize-$toCombinationSize"
}

internal class StrictVariablePerms(minUnitSize: Int, maxUnitSize: Int) : BaseVariablePerms(minUnitSize, maxUnitSize, false) {

   override fun toString(): String = "StrictPerms$fromCombinationSize-$toCombinationSize"
}
