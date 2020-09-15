package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.*

fun requireItemCountAtLeast(itemCount: Int, value: Int) {
   require(itemCount >= value) { "itemCount must be greater than or equal to $value" }
}

fun <T> requireItemsSize(items: List<T>, value: Int) {
   require(items.size == value) { "legs/groups size must be $value" }
}

fun <T> requireItemsSizeAtLeast(items: List<T>, value: Int) {
   require(items.size >= value) { "legs/groups size must be greater than or equal to $value" }
}

fun validateUnitCountFactor(unitCountFactor: Decimal) {
   require(unitCountFactor > ZERO) { "unitCountFactor must be positive" }
}

fun validateStakeFactorCarriedForward(stakeFactorCarriedForward: Decimal) {
   require(stakeFactorCarriedForward > ZERO) { "stakeFactorCarriedForward must be positive" }
}
