package org.strangeforest.betcalculator.util

import java.math.*
import java.math.RoundingMode.*

private val MATH_CONTEXT = MathContext(20, HALF_EVEN)

actual class Decimal(private val value: BigDecimal) : Comparable<Decimal> {

   actual constructor(value: Int) : this(BigDecimal(value))
   actual constructor(value: String) : this(BigDecimal(value))

   actual operator fun plus(other: Decimal): Decimal = Decimal(value + other.value)
   actual operator fun minus(other: Decimal): Decimal = Decimal(value - other.value)
   actual operator fun times(other: Decimal): Decimal = Decimal(value * other.value)
   actual operator fun div(other: Decimal): Decimal = Decimal(value.divide(other.value, MATH_CONTEXT))

   actual fun toDouble(): Double = value.toDouble()

   actual override fun equals(other: Any?): Boolean = this === other || (other is Decimal && value == other.value)
   actual override fun hashCode(): Int = value.hashCode()
   override fun compareTo(other: Decimal): Int = value.compareTo(other.value)
   actual override fun toString(): String = value.toString()
}
