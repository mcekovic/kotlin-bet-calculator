package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import java.math.BigDecimal.*

class LapTest {

   @Test
   fun invalidStraightFullCoverTest() {
      assertFails { Lap(ZERO) }
   }
}
