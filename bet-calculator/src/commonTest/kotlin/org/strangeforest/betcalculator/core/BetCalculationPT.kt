package org.strangeforest.betcalculator.core

import kotlin.test.*
import assertk.*
import org.strangeforest.betcalculator.bettypes.*

class BetCalculationPT {

   @Test @Ignore
   fun bigPermsTest() {
      val bet = Bet(FullCoverWithSinglesN(22), "1", makeBetLegs(22))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).isResult(4194303, "4194303", "310224200866619719679999")
   }
}