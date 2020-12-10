package org.strangeforest.betcalculator.util

import java.math.*
import java.math.RoundingMode.*

val TWO: BigDecimal = BigDecimal("2")
val HALF: BigDecimal = BigDecimal("0.5")

private val MATH_CONTEXT = MathContext(20, HALF_EVEN)

operator fun BigDecimal.div(other: BigDecimal): BigDecimal {
   return this.divide(other, MATH_CONTEXT)
}
