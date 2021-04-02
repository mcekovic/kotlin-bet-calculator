package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayType.*
import org.strangeforest.betcalculator.util.*

internal data class LegStatus(
   val winFactor: Decimal,
   val voidFactor: Decimal,
   val placeOddsFactor: Decimal = ONE,
   val resulted: Boolean = false
) {

   val state: BetState

   init {
      require(winFactor >= ZERO) { "winFactor must not be negative" }
      require(voidFactor >= ZERO) { "voidFactor must not be negative" }
      require(placeOddsFactor >= ZERO) { "placeOddsFactor must not be negative" }
      this.state = when {
         !resulted -> BetState.OPEN
         winFactor > ZERO -> BetState.WON
         voidFactor > ZERO -> BetState.VOID
         else -> BetState.LOST
      }
   }

   fun factoredPrice(price: Decimal, eachWayType: EachWayType = WIN): Decimal = winFactor * applyOddsFactor(price, eachWayType) + voidFactor

   private fun applyOddsFactor(price: Decimal, eachWayType: EachWayType): Decimal = when (eachWayType) {
      WIN -> price
      PLACE -> ONE + (price - ONE) * placeOddsFactor
      EACH_WAY -> throw IllegalStateException()
   }

   fun isOpen(): Boolean = state == BetState.OPEN
   fun isWon(): Boolean = state == BetState.WON
   fun isVoid(): Boolean = state == BetState.VOID
   fun isLost(): Boolean = state == BetState.LOST

   companion object {

      val OPEN = open()
      val WON = won()
      val VOID = void()
      val LOST = lost()

      fun open(placeOddsFactor: Decimal = ONE): LegStatus = LegStatus(ONE, ZERO, placeOddsFactor)
      fun open(placeOddsFactor: String): LegStatus = open(placeOddsFactor.dec)
      fun won(placeOddsFactor: Decimal = ONE): LegStatus = resulted(ONE, ZERO, placeOddsFactor)
      fun won(placeOddsFactor: String): LegStatus = won(placeOddsFactor.dec)
      fun void(placeOddsFactor: Decimal = ONE): LegStatus = resulted(ZERO, ONE, placeOddsFactor)
      fun void(placeOddsFactor: String): LegStatus = void(placeOddsFactor.dec)
      fun lost(placeOddsFactor: Decimal = ONE): LegStatus = resulted(ZERO, ZERO, placeOddsFactor)
      fun lost(placeOddsFactor: String): LegStatus = lost(placeOddsFactor.dec)

      fun resulted(winFactor: Decimal, voidFactor: Decimal, placeOddsFactor: Decimal = ONE): LegStatus =
         LegStatus(winFactor, voidFactor, placeOddsFactor, true)

      fun resulted(winFactor: String, voidFactor: String, placeOddsFactor: String = ONE.toString()): LegStatus =
         resulted(winFactor.dec, voidFactor.dec, placeOddsFactor.dec)
   }
}
