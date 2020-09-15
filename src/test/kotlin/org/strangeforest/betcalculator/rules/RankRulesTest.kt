package org.strangeforest.betcalculator.rules

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.interrelation.*

class RankRulesTest {

   @Test
   fun openStatusTest() {
      assertThat(RankRules().openStatus).isEqualTo(LegStatus.OPEN)
   }

   @Test
   fun resultedStatusTest() {
      val leg = BetLeg("2")
      val multiWinnerLeg = BetLeg("2", irDescriptor = IRDescriptor(1, 1, 1, 2))
      assertThat(RankRules().resultedStatus(leg, Rank(1, 1))).isEqualTo(LegStatus.WON)
      assertThat(RankRules().resultedStatus(leg, Rank(1, 2))).isEqualTo(LegStatus.resulted("0.5", "0"))
      assertThat(RankRules().resultedStatus(leg, Rank(2, 1))).isEqualTo(LegStatus.LOST)
      assertThat(RankRules().resultedStatus(leg, Rank.NON_RUNNER)).isEqualTo(LegStatus.VOID)
      assertThat(RankRules().resultedStatus(multiWinnerLeg, Rank(1, 2))).isEqualTo(LegStatus.WON)
   }
}
