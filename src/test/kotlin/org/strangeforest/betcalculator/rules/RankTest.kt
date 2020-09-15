package org.strangeforest.betcalculator.rules

import kotlin.test.*
import assertk.*
import org.strangeforest.betcalculator.*

class RankTest {

   @Test
   fun isWonTest() {
      assertTrue(Rank(1, 1).isWon())
      assertTrue(Rank(1, 3).isWon())
      assertFalse(Rank(2, 1).isWon())
      assertFalse(Rank.NON_RUNNER.isWon())
      assertFalse(Rank.NOT_PLACED.isWon())
   }

   @Test
   fun isPlacedTest() {
      val placeTerms = PlaceTerms(4, "5")
      assertTrue(Rank(3, 3).isPlaced(placeTerms))
      assertTrue(Rank(1, 1).isPlaced(PlaceTerms.WIN_ONLY))
      assertFalse(Rank(5, 1).isPlaced(placeTerms))
      assertFalse(Rank.NON_RUNNER.isPlaced(placeTerms))
      assertFalse(Rank.NOT_PLACED.isPlaced(placeTerms))
   }

   @Test
   fun deadHeatFactorTest() {
      assertThat(Rank(1, 1).deadHeatFactor()).isEqualByComparingTo("1")
      assertThat(Rank(1, 2).deadHeatFactor()).isEqualByComparingTo("0.5")
      val placeTerms = PlaceTerms(4, "5")
      assertThat(Rank(3, 1).deadHeatFactor(placeTerms)).isEqualByComparingTo("1")
      assertThat(Rank(3, 2).deadHeatFactor(placeTerms)).isEqualByComparingTo("1")
      assertThat(Rank(3, 4).deadHeatFactor(placeTerms)).isEqualByComparingTo("0.5")
      assertThat(Rank.NON_RUNNER.deadHeatFactor(placeTerms)).isEqualByComparingTo("0")
      assertThat(Rank.NOT_PLACED.deadHeatFactor(placeTerms)).isEqualByComparingTo("0")
   }

   @Test
   fun invalidRankTest() {
      assertFails { Rank(-1, 1) }
      assertFails { Rank(1, -1) }
   }
}
