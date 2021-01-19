package org.strangeforest.betcalculator.core

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.core.BetState.*

class BetStateTest {

   @Test
   fun plusTest() {
      assertThat(OPEN + OPEN).isEqualTo(OPEN)
      assertThat(OPEN + WON).isEqualTo(OPEN)
      assertThat(OPEN + VOID).isEqualTo(OPEN)
      assertThat(OPEN + LOST).isEqualTo(OPEN)

      assertThat(WON + OPEN).isEqualTo(OPEN)
      assertThat(WON + WON).isEqualTo(WON)
      assertThat(WON + VOID).isEqualTo(WON)
      assertThat(WON + LOST).isEqualTo(WON)

      assertThat(VOID + OPEN).isEqualTo(OPEN)
      assertThat(VOID + WON).isEqualTo(WON)
      assertThat(VOID + VOID).isEqualTo(VOID)
      assertThat(VOID + LOST).isEqualTo(VOID)

      assertThat(LOST + OPEN).isEqualTo(OPEN)
      assertThat(LOST + WON).isEqualTo(WON)
      assertThat(LOST + VOID).isEqualTo(VOID)
      assertThat(LOST + LOST).isEqualTo(LOST)
   }

   @Test
   fun timesTest() {
      assertThat(OPEN * OPEN).isEqualTo(OPEN)
      assertThat(OPEN * WON).isEqualTo(OPEN)
      assertThat(OPEN * VOID).isEqualTo(OPEN)
      assertThat(OPEN * LOST).isEqualTo(LOST)

      assertThat(WON * OPEN).isEqualTo(OPEN)
      assertThat(WON * WON).isEqualTo(WON)
      assertThat(WON * VOID).isEqualTo(WON)
      assertThat(WON * LOST).isEqualTo(LOST)

      assertThat(VOID * OPEN).isEqualTo(OPEN)
      assertThat(VOID * WON).isEqualTo(WON)
      assertThat(VOID * VOID).isEqualTo(VOID)
      assertThat(VOID * LOST).isEqualTo(LOST)

      assertThat(LOST * OPEN).isEqualTo(LOST)
      assertThat(LOST * WON).isEqualTo(LOST)
      assertThat(LOST * VOID).isEqualTo(LOST)
      assertThat(LOST * LOST).isEqualTo(LOST)
   }
}
