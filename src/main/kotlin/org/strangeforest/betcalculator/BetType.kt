package org.strangeforest.betcalculator

import java.math.*
import org.strangeforest.betcalculator.rules.*

abstract class BetType(val canSkipUnits: Boolean = false) {

   abstract fun <T> combinations(items: List<T>): Sequence<Combination<T>>

   fun <T : BankerAware> bankerAwareCombinations(items: List<T>): Sequence<Combination<T>> {
      val bankerItems = items.filter(BankerAware::banker)
      return if (bankerItems.isEmpty())
         combinations(items)
      else
         combinations(items.filterNot(BankerAware::banker))
            .map { combination -> combination.withBankers(bankerItems) }
   }

   fun <T> toCombination(items: List<T>): Combination<T> = Combination(items, this)

   open fun createUnit(unitStake: BigDecimal, legs: List<BetLeg>, rules: BetRules): BetUnit = BetUnit(unitStake, legs, this, rules)

   override fun toString(): String = this::class.simpleName.toString()
}
