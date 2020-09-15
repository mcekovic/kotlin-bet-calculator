package org.strangeforest.betcalculator.rules

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.interrelation.*
import org.strangeforest.betcalculator.rules.EachWayType.*

class EachWayRulesTest {

   @Test
   fun openStatusTest() {
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).openStatus).isEqualTo(LegStatus.OPEN)
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).openStatus).isEqualTo(LegStatus.open("0.2"))
   }

   @Test
   fun resultedStatusTest() {
      val leg = BetLeg("2")
      val multiWinnerLeg = BetLeg("2", irDescriptor = IRDescriptor(1, 1, 1, 2))
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).resultedStatus(leg, Rank(1, 1))).isEqualTo(LegStatus.WON)
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).resultedStatus(leg, Rank(2, 1))).isEqualTo(LegStatus.won("0.2"))
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).resultedStatus(leg, Rank(2, 1))).isEqualTo(LegStatus.LOST)
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).resultedStatus(leg, Rank(5, 1))).isEqualTo(LegStatus.lost("0.2"))
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).resultedStatus(leg, Rank(1, 2))).isEqualTo(LegStatus.resulted("0.5", "0"))
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).resultedStatus(multiWinnerLeg, Rank(1, 2))).isEqualTo(LegStatus.WON)
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).resultedStatus(leg, Rank(3, 4))).isEqualTo(LegStatus.resulted("0.5", "0", "0.2"))
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).resultedStatus(multiWinnerLeg, Rank(3, 4))).isEqualTo(LegStatus.resulted("1", "0", "0.2"))
      assertThat(EachWayRules(WIN, PlaceTerms(4, "5")).resultedStatus(leg, Rank.NON_RUNNER)).isEqualTo(LegStatus.VOID)
      assertThat(EachWayRules(WIN, PlaceTerms(3, "1")).resultedStatus(leg, Rank(2, 1))).isEqualTo(LegStatus.WON)
      assertThat(EachWayRules(WIN, PlaceTerms(3, "1")).resultedStatus(leg, Rank(4, 1))).isEqualTo(LegStatus.LOST)
      assertThat(EachWayRules(PLACE, PlaceTerms(4, "5")).resultedStatus(leg, Rank.NON_RUNNER)).isEqualTo(LegStatus.void("0.2"))
      assertThat(EachWayRules(PLACE, PlaceTerms(1, "1")).resultedStatus(leg, Rank(2, 1))).isEqualTo(LegStatus.VOID)
   }

   @Test
   fun invalidEachWayRulesTest() {
      assertFails { EachWayRules(EACH_WAY, PlaceTerms(3, "4")) }
   }
}
