package org.strangeforest.betcalculator.bettypes

import kotlin.test.*

class PermedSubTypeTest {

   @Test
   fun invalidStraightFullCoverTest() {
      assertFails { PermedSubType(Single, 0) }
   }
}
