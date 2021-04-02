package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

internal abstract class BasePerms(protected val combinationSize: Int, canSkipUnits: Boolean = true) : BetType(canSkipUnits) {

   init {
      require(combinationSize > 0) { "combinationSize must be positive" }
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, combinationSize)
      return combinations(items, combinationSize).map(::toCombination)
   }
}

internal class Perms(unitSize: Int) : BasePerms(unitSize) {

   override fun toString(): String = super.toString() + combinationSize
}

internal open class StrictPerms(unitSize: Int) : BasePerms(unitSize, false) {

   override fun toString(): String = super.toString() + combinationSize
}

internal open class StrictPermsN(unitSize: Int, val itemCount: Int) : StrictPerms(unitSize) {

   init {
      requireItemCountAtLeast(itemCount, unitSize)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

internal class ReducedStakeStrictPermsN(unitSize: Int, itemCount: Int, val unitCountFactor: Decimal) : StrictPermsN(unitSize, itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      BetUnit(unitStake, legs, this, rules, unitCountFactor)
}

internal object Singles : BasePerms(1)
internal object Doubles : BasePerms(2)
internal object Trebles : BasePerms(3)
internal object Fourfolds : BasePerms(4)
internal object Fivefolds : BasePerms(5)
internal object Sixfolds : BasePerms(6)
internal object Sevenfolds : BasePerms(7)
internal object Eightfolds : BasePerms(8)
