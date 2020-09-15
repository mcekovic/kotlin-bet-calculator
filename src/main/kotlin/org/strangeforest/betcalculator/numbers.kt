package org.strangeforest.betcalculator

import java.math.*
import java.math.RoundingMode.*

val ZERO: Decimal = DDecimal(0)
val ONE: Decimal = DDecimal(1)
val TWO: Decimal = DDecimal(2)
val HALF: Decimal = DDecimal("0.5")

val String.dec: Decimal
   get() = DDecimal(this)

val Int.dec: Decimal
   get() = DDecimal(this)

//val ZERO: Decimal = BDDecimal(BigDecimal.ZERO)
//val ONE: Decimal = BDDecimal(BigDecimal.ONE)
//val TWO: Decimal = BDDecimal("2")
//val HALF: Decimal = BDDecimal("0.5")
//
//val String.dec: Decimal
//   get() = BDDecimal(this)
//
//val Int.dec: Decimal
//   get() = BDDecimal(this)

private val MATH_CONTEXT = MathContext(20, HALF_EVEN)

interface Decimal : Comparable<Decimal> {

   operator fun plus(other: Decimal): Decimal
   operator fun minus(other: Decimal): Decimal
   operator fun times(other: Decimal): Decimal
   operator fun div(other: Decimal): Decimal

   fun toDouble(): Double
}

private class BDDecimal(val value: BigDecimal) : Decimal {

   constructor(value: Int) : this(BigDecimal.valueOf(value.toLong()))
   constructor(value: String) : this(BigDecimal(value))

   override fun plus(other: Decimal): Decimal = BDDecimal(if (other is BDDecimal) value + other.value else (BigDecimal.valueOf(toDouble() + other.toDouble())))
   override fun minus(other: Decimal): Decimal = BDDecimal(if (other is BDDecimal) value - other.value else (BigDecimal.valueOf(toDouble() - other.toDouble())))
   override fun times(other: Decimal): Decimal = BDDecimal(if (other is BDDecimal) value * other.value else (BigDecimal.valueOf(toDouble() * other.toDouble())))
   override fun div(other: Decimal): Decimal = BDDecimal(if (other is BDDecimal) value.divide(other.value, MATH_CONTEXT) else (BigDecimal.valueOf(toDouble() / other.toDouble())))

   override fun toDouble(): Double = value.toDouble()

   override fun equals(other: Any?): Boolean = this === other || (other is BDDecimal && value == other.value) || (other is Decimal && toDouble() == other.toDouble())
   override fun hashCode(): Int = value.hashCode()
   override fun compareTo(other: Decimal): Int = if (other is BDDecimal) value.compareTo(other.value) else toDouble().compareTo(other.toDouble())
   override fun toString(): String = value.toString()
}

private class DDecimal(val value: Double) : Decimal {

   constructor(value: Int) : this(value.toDouble())
   constructor(value: String) : this(value.toDouble())

   override fun plus(other: Decimal): Decimal = DDecimal(if (other is DDecimal) value + other.value else (toDouble() + other.toDouble()))
   override fun minus(other: Decimal): Decimal = DDecimal(if (other is DDecimal) value - other.value else (toDouble() - other.toDouble()))
   override fun times(other: Decimal): Decimal = DDecimal(if (other is DDecimal) value * other.value else (toDouble() * other.toDouble()))
   override fun div(other: Decimal): Decimal = DDecimal(if (other is DDecimal) value / other.value else (toDouble() / other.toDouble()))

   override fun toDouble(): Double = value

   override fun equals(other: Any?): Boolean = this === other || (other is DDecimal && value == other.value) || (other is Decimal && value == other.toDouble())
   override fun hashCode(): Int = value.hashCode()
   override fun compareTo(other: Decimal): Int = if (other is DDecimal) value.compareTo(other.value) else value.compareTo(other.toDouble())
   override fun toString(): String = value.toString()
}
