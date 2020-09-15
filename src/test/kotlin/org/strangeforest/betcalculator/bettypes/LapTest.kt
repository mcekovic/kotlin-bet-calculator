package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import org.strangeforest.betcalculator.*

class LapTest {

   @Test
   fun invalidStraightFullCoverTest() {
      assertFails { Lap(ZERO) }
   }
}
