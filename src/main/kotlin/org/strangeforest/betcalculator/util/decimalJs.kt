package org.strangeforest.betcalculator.util

//actual class Decimal actual constructor(private val value: Double) : Comparable<Decimal> {
//
//   actual constructor(value: Int) : this(value.toDouble())
//   actual constructor(value: String) : this(value.toDouble())
//
//   actual operator fun plus(other: Decimal): Decimal = Decimal(value + other.value)
//   actual operator fun minus(other: Decimal): Decimal = Decimal(value - other.value)
//   actual operator fun times(other: Decimal): Decimal = Decimal(value * other.value)
//   actual operator fun div(other: Decimal): Decimal = Decimal(value / other.value)
//
//   actual fun toDouble(): Double = value.toDouble()
//
//   actual override fun equals(other: Any?): Boolean = this === other || (other is Decimal && value == other.value)
//   actual override fun hashCode(): Int = value.hashCode()
//   override fun compareTo(other: Decimal): Int = value.compareTo(other.value)
//   actual override fun toString(): String = value.toString()
//}
