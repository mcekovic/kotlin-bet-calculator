package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class UpAndDownTest {

   @Test
   fun toStringTest() {
      assertThat(SingleStakesAboutN(5)).hasToString("SingleStakesAbout5")
      assertThat(DoubleStakesAboutN(5)).hasToString("DoubleStakesAbout5")
      assertThat(SingleStakesAbout).hasToString("SingleStakesAbout")
      assertThat(RoundRobin).hasToString("RoundRobin")
   }

   @Test
   fun invalidUpAndDownTest() {
      assertFails { SingleStakesAboutN(1) }
      assertFails { DoubleStakesAboutN(1) }
   }
}
