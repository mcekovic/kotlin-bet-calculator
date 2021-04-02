package org.strangeforest.betcalculator

import kotlin.test.*
import assertk.*
import assertk.assertions.*

internal class BetCaptureCalculationTest {

   @Test
   fun calculateSingleBet() {
      val bet = Bet(
         "Single", "10", arrayOf(
            BetLeg("2.0")
         )
      )
      val result = calculateCapture(bet)
      assertThat(result.maxReturn).isEqualTo("20.0")
   }

   @Test
   fun calculateAccumulatorBet() {
      val bet = Bet(
         "Accumulator", "2", arrayOf(
            BetLeg("2.0"),
            BetLeg("3.0")
         )
      )
      val result = calculateCapture(bet)
      assertThat(result.maxReturn).isEqualTo("12.00")
   }

   @Test
   fun calculateDoublesBet() {
      val bet = Bet(
         "Doubles", "1", arrayOf(
            BetLeg("2.0"),
            BetLeg("3.0"),
            BetLeg("4.0")
         )
      )
      val result = calculateCapture(bet)
      assertThat(result.maxReturn).isEqualTo("26.00")
   }

   @Test
   fun calculateBigPermsBet() {
      val bet = Bet(
         "Perms10", "1", arrayOf(
            BetLeg("1.1"),
            BetLeg("1.2"),
            BetLeg("1.4"),
            BetLeg("1.6"),
            BetLeg("1.8"),
            BetLeg("2.0"),
            BetLeg("3.0"),
            BetLeg("4.0"),
            BetLeg("5.0"),
            BetLeg("6.0"),
            BetLeg("1.1"),
            BetLeg("1.2"),
            BetLeg("1.4"),
            BetLeg("1.6"),
            BetLeg("1.8"),
            BetLeg("2.0"),
            BetLeg("3.0"),
            BetLeg("4.0"),
            BetLeg("5.0"),
            BetLeg("6.0")
         )
      )
      val result = calculateCapture(bet)
      assertThat(result.maxReturn).isEqualTo("1662209157.7375774976")
   }

   @Test
   fun calculatePermsBetWithAccumulatorGroups() {
      val bet = Bet("Perms2", "1", emptyArray())
      bet.groups = arrayOf(
         BetLegGroup("Accumulator", arrayOf(
            BetLeg("2"),
            BetLeg("3")
         )),
         BetLegGroup("Accumulator", arrayOf(
            BetLeg("4")
         )),
         BetLegGroup("Accumulator", arrayOf(
            BetLeg("5")
         ))
      )

      val result = calculateCapture(bet)

      assertThat(result.maxReturn).isEqualTo("74")
   }
}
