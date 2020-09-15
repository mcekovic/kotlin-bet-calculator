package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import org.strangeforest.betcalculator.*

class PermsAnyToComeRestTest {

   @Test
   fun invalidPermsAnyToComeRestTest() {
      assertFails { object : PermsAnyToComeRest(ZERO, 1) {} }
      assertFails { object : PermsAnyToComeRest(ONE, 0,) {}  }
      assertFails { object : PermsAnyToComeRestN(ONE, 1, 0) {} }
      assertFails { ReducedStakePermsAnyToComeRestN(ONE, 1, 3, ZERO) }
   }
}
