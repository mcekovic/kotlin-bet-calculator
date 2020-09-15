package org.strangeforest.betcalculator

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.BetState.*

class BetUnitTest {

   @Test
   fun openUnitTest() {
      val unit = BetUnit("10", listOf(
         BetLeg("2", LegStatus.WON),
         BetLeg("3", LegStatus.OPEN)
      ))

      assertThat(unit.maxReturn).isEqualByComparingTo("60")
      assertThat(unit.state).isEqualTo(OPEN)
   }

   @Test
   fun wonUnitTest() {
      val unit = BetUnit("10", listOf(
         BetLeg("2", LegStatus.WON),
         BetLeg("3", LegStatus.VOID)
      ))

      assertThat(unit.maxReturn).isEqualByComparingTo("20")
      assertThat(unit.state).isEqualTo(WON)
   }

   @Test
   fun voidUnitTest() {
      val unit = BetUnit("10", listOf(
         BetLeg("2", LegStatus.VOID),
         BetLeg("3", LegStatus.VOID)
      ))

      assertThat(unit.maxReturn).isEqualByComparingTo("10")
      assertThat(unit.state).isEqualTo(VOID)
   }

   @Test
   fun lostUnitTest() {
      val unit = BetUnit("10", listOf(
         BetLeg("2", LegStatus.OPEN),
         BetLeg("3", LegStatus.LOST)
      ))

      assertThat(unit.maxReturn).isEqualByComparingTo("0")
      assertThat(unit.state).isEqualTo(LOST)
   }
}
