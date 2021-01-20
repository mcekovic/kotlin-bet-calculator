package org.strangeforest.betcalculator.core

internal enum class BetState {

   OPEN, WON, VOID, LOST;

   operator fun plus(other: BetState): BetState = minOf(this, other)

   operator fun times(other: BetState): BetState = when {
      this == LOST || other == LOST -> LOST
      else -> minOf(this, other)
   }
}
