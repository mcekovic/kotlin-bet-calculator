package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class VariablePermsTest {

   @Test
   fun toStringTest() {
      assertThat(VariablePerms(3, 5)).hasToString("Perms3-5")
      assertThat(StrictVariablePerms(3, 5)).hasToString("StrictPerms3-5")
   }

   @Test
   fun invalidVariablePermsTest() {
      assertFails { VariablePerms(0, 5) }
      assertFails { VariablePerms(3, 2) }
      assertFails { StrictVariablePerms(0, 5) }
      assertFails { StrictVariablePerms(3, 2) }
   }
}
