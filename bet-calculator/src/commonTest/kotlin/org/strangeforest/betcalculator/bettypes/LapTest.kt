package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import org.strangeforest.betcalculator.util.*

class LapTest {

   @Test
   fun invalidStraightFullCoverTest() {
      assertFails { Lap(ZERO) }
   }
}
