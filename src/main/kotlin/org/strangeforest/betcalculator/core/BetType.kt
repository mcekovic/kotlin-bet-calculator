package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.bettypes.CompoundBetType
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

internal abstract class BetType(val canSkipUnits: Boolean = false) {

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

   open fun createUnit(unitStake: Decimal, legs: List<BetLeg>, rules: BetRules): BetUnit = BetUnit(unitStake, legs, this, rules)

   operator fun plus(betType: BetType): BetType = CompoundBetType(this, betType)

   override fun toString(): String = this::class.simpleName.toString()
}
