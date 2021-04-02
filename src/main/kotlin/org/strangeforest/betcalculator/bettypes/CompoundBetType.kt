package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*

internal abstract class BaseCompoundBetType(vararg val betTypes: BetType) : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> = sequenceOf(*betTypes)
      .map { betType -> betType.combinations(items) }
      .flatten()
}

internal class CompoundBetType(vararg betTypes: BetType) : BaseCompoundBetType(*betTypes) {

   override fun toString(): String = betTypes.joinToString("+")
}
