package org.strangeforest.betcalculator.util

expect class Decimal(value: String) : Comparable<Decimal> {

   constructor(value: Int)

   operator fun plus(other: Decimal): Decimal
   operator fun minus(other: Decimal): Decimal
   operator fun times(other: Decimal): Decimal
   operator fun div(other: Decimal): Decimal

   fun toDouble(): Double

   override fun equals(other: Any?): Boolean
   override fun hashCode(): Int
   override fun toString(): String
}

val String.dec: Decimal
   get() = Decimal(this)

val Int.dec: Decimal
   get() = Decimal(this)

val ZERO = Decimal("0")
val ONE = Decimal("1")
val TWO = Decimal("2")
val HALF = Decimal("0.5")
