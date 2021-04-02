package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.util.*

internal fun requireItemCountAtLeast(itemCount: Int, value: Int) {
   require(itemCount >= value) { "itemCount must be greater than or equal to $value" }
}

internal fun <T> requireItemsSize(items: List<T>, value: Int) {
   require(items.size == value) { "legs/groups size must be $value" }
}

internal fun <T> requireItemsSizeAtLeast(items: List<T>, value: Int) {
   require(items.size >= value) { "legs/groups size must be greater than or equal to $value" }
}

internal fun validateUnitCountFactor(unitCountFactor: Decimal) {
   require(unitCountFactor > ZERO) { "unitCountFactor must be positive" }
}

internal fun validateStakeFactorCarriedForward(stakeFactorCarriedForward: Decimal) {
   require(stakeFactorCarriedForward > ZERO) { "stakeFactorCarriedForward must be positive" }
}
