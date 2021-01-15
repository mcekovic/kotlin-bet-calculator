package org.strangeforest.betcalculator.util

class DoubleDecimal(private val value: Double) : Comparable<DoubleDecimal> {

	constructor(value: Int) : this(value.toDouble())
	constructor(value: String) : this(value.toDouble())

	operator fun plus(other: DoubleDecimal): DoubleDecimal = DoubleDecimal(value + other.value)
	operator fun minus(other: DoubleDecimal): DoubleDecimal = DoubleDecimal(value - other.value)
	operator fun times(other: DoubleDecimal): DoubleDecimal = DoubleDecimal(value * other.value)
	operator fun div(other: DoubleDecimal): DoubleDecimal = DoubleDecimal(value / other.value)

	fun toDouble(): Double = value

	override fun equals(other: Any?): Boolean = this === other || (other is DoubleDecimal && value == other.value)
	override fun hashCode(): Int = value.hashCode()
	override fun compareTo(other: DoubleDecimal): Int = value.compareTo(other.value)
	override fun toString(): String = value.toString()
}
