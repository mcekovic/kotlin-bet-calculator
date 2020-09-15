package org.strangeforest.betcalculator.bettypes

import java.math.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.rules.*

abstract class BasePerms(protected val combinationSize: Int, canSkipUnits: Boolean = true) : BetType(canSkipUnits) {

   init {
      require(combinationSize > 0) { "combinationSize must be positive" }
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSizeAtLeast(items, combinationSize)
      return combinations(items, combinationSize).map(::toCombination)
   }
}

class Perms(unitSize: Int) : BasePerms(unitSize) {

   override fun toString(): String = super.toString() + combinationSize
}

open class StrictPerms(unitSize: Int) : BasePerms(unitSize, false) {

   override fun toString(): String = super.toString() + combinationSize
}

open class StrictPermsN(unitSize: Int, val itemCount: Int) : StrictPerms(unitSize) {

   init {
      requireItemCountAtLeast(itemCount, unitSize)
   }

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, itemCount)
      return super.combinations(items)
   }
}

class ReducedStakeStrictPermsN(unitSize: Int, itemCount: Int, val unitCountFactor: BigDecimal) : StrictPermsN(unitSize, itemCount) {

   init {
      validateUnitCountFactor(unitCountFactor)
   }

   override fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit =
      BetUnit(unitStake, legs, this, rules, unitCountFactor)
}

object Singles : BasePerms(1)
object Doubles : BasePerms(2)
object Trebles : BasePerms(3)
object Fourfolds : BasePerms(4)
object Fivefolds : BasePerms(5)
object Sixfolds : BasePerms(6)
object Sevenfolds : BasePerms(7)
object Eightfolds : BasePerms(8)
