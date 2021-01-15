package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class CompoundBetTypeTest {

   @Test
   fun toStringTest() {
      assertThat(CompoundBetType(Doubles, Trebles)).hasToString("Doubles+Trebles")
   }
}
