package org.strangeforest.betcalculator.core

import kotlin.test.*
import org.strangeforest.betcalculator.bettypes.*

class BetTest {

   @Test
   fun invalidBetTest() {
      assertFails { Bet(Single, "0", makeBetLegs(1)) }
      assertFails { Bet(Single, "1") }
      assertFails { Bet(Single, "1", makeBetLegs(1), listOf(BetLegGroup(Single, makeBetLegs(1)))) }
      assertFails { Bet(Single, "1", listOf(BetLeg("0"))) }
      assertFails { Bet(Single, "1", groups = listOf(BetLegGroup(Single, listOf()))) }
   }
}
