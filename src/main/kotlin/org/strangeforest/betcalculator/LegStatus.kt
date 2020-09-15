package org.strangeforest.betcalculator

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayType.*

data class LegStatus(
   val winFactor: BigDecimal,
   val voidFactor: BigDecimal,
   val placeOddsFactor: BigDecimal = ONE,
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

   fun factoredPrice(price: BigDecimal, eachWayType: EachWayType = WIN): BigDecimal = winFactor * applyOddsFactor(price, eachWayType) + voidFactor

   private fun applyOddsFactor(price: BigDecimal, eachWayType: EachWayType): BigDecimal = when (eachWayType) {
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

      fun open(placeOddsFactor: BigDecimal = ONE): LegStatus = LegStatus(ONE, ZERO, placeOddsFactor)
      fun open(placeOddsFactor: String): LegStatus = open(placeOddsFactor.toBigDecimal())
      fun won(placeOddsFactor: BigDecimal = ONE): LegStatus = resulted(ONE, ZERO, placeOddsFactor)
      fun won(placeOddsFactor: String): LegStatus = won(placeOddsFactor.toBigDecimal())
      fun void(placeOddsFactor: BigDecimal = ONE): LegStatus = resulted(ZERO, ONE, placeOddsFactor)
      fun void(placeOddsFactor: String): LegStatus = void(placeOddsFactor.toBigDecimal())
      fun lost(placeOddsFactor: BigDecimal = ONE): LegStatus = resulted(ZERO, ZERO, placeOddsFactor)
      fun lost(placeOddsFactor: String): LegStatus = lost(placeOddsFactor.toBigDecimal())

      fun resulted(winFactor: BigDecimal, voidFactor: BigDecimal, placeOddsFactor: BigDecimal = ONE): LegStatus =
         LegStatus(winFactor, voidFactor, placeOddsFactor, true)

      fun resulted(winFactor: String, voidFactor: String, placeOddsFactor: String = ONE.toString()): LegStatus =
         resulted(winFactor.toBigDecimal(), voidFactor.toBigDecimal(), placeOddsFactor.toBigDecimal())
   }
}
