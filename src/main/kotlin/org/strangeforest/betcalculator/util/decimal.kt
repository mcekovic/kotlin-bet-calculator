package org.strangeforest.betcalculator.util

expect class Decimal : Comparable<Decimal> {

   constructor(value: Int)
   constructor(value: Double)
   constructor(value: String)

   operator fun plus(other: Decimal): Decimal
   operator fun minus(other: Decimal): Decimal
   operator fun times(other: Decimal): Decimal
   operator fun div(other: Decimal): Decimal

   fun toDouble(): Double

   override fun equals(other: Any?): Boolean
   override fun hashCode(): Int
   override fun compareTo(other: Decimal): Int
   override fun toString(): String
}

val Int.dec: Decimal
   get() = Decimal(this)

val Double.dec: Decimal
   get() = Decimal(this)

val String.dec: Decimal
   get() = Decimal(this)

val ZERO = Decimal("0")
val ONE = Decimal("1")
val TWO = Decimal("2")
val HALF = Decimal("0.5")
