package org.strangeforest.betcalculator.bettypes

import kotlin.test.*

class StraightFullCoverTest {

   @Test
   fun invalidStraightFullCoverTest() {
      assertFails { StraightFullCover(0) }
   }
}
