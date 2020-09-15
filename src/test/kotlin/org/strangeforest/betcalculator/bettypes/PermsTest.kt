package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class PermsTest {

   @Test
   fun toStringTest() {
      assertThat(Perms(5)).hasToString("Perms5")
      assertThat(Doubles).hasToString("Doubles")
   }

   @Test
   fun invalidPermsTest() {
      assertFails { Perms(0) }
   }
}
