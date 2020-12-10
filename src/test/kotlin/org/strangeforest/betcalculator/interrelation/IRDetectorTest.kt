package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*

class IRDetectorTest {

   @Test
   fun differentSelectionsMarketaAndEventsAreNotInterrelated() {
      val desc1 = IRDescriptor(111, 11, 1)
      val desc2 = IRDescriptor(211, 21, 2)
      assertThat(desc1).isNotInterrelatedWith(desc2)
   }

   @Test
   fun sameSelectionsAreInterrelated() {
      val desc1 = IRDescriptor(111, 11, 1)
      val desc2 = IRDescriptor(111, 11, 1)
      assertThat(desc1).isInterrelatedWith(desc2)
   }

   @Test
   fun sameMarketsButDifferentSelectionsAreInterrelated() {
      val desc1 = IRDescriptor(111, 11, 1)
      val desc2 = IRDescriptor(112, 11, 1)
      assertThat(desc1).isInterrelatedWith(desc2)
   }

   @Test
   fun sameEventButDifferentMarketsAreInterrelatedIfOutcomeMatches() {
      val desc1 = IRDescriptor(111, 11, 1, tag = IRTag.of("O:Score"))
      val desc2 = IRDescriptor(121, 12, 1, tag = IRTag.of("O:Score"))
      assertThat(desc1).isInterrelatedWith(desc2)
   }

   @Test
   fun sameEventButDifferentMarketsAreNotInterrelatedIfOutcomeDoesNotMatch() {
      val desc1 = IRDescriptor(111, 11, 1, tag = IRTag.of("O:Corners"))
      val desc2 = IRDescriptor(121, 12, 1, tag = IRTag.of("O:Cartons"))
      assertThat(desc1).isNotInterrelatedWith(desc2)
   }

   @Test
   fun differentEventsGroupInterrelation() {
      val desc1 = IRDescriptor(111, 11, 1, tag = IRTag.of("O:Rank,Corners;G:123,456"))
      val desc2 = IRDescriptor(211, 21, 2, tag = IRTag.of("O:Score,Rank;G:456,789"))
      assertThat(desc1).isInterrelatedWith(desc2)
   }

   @Test
   fun differentEventsCauseConsequenceInterrelation() {
      val desc1 = IRDescriptor(111, 11, 1, tag = IRTag.of("O:Score;C:123,456,789>333"))
      val desc2 = IRDescriptor(211, 21, 2, tag = IRTag.of("D:Score,Rank;C:777,888>456,789,999"))
      assertThat(desc1).isInterrelatedWith(desc2)
      val desc3 = IRDescriptor(111, 11, 1, tag = IRTag.of("D:Score;C:123,456,789>333"))
      val desc4 = IRDescriptor(211, 21, 2, tag = IRTag.of("O:Score,Rank;C:777,333>999"))
      assertThat(desc3).isInterrelatedWith(desc4)
   }

   @Test
   fun noInterrelationOverride() {
      val desc1 = IRDescriptor(111, 11, 1, noInterrelation = true)
      val desc2 = IRDescriptor(112, 11, 1)
      assertThat(desc1).isNotInterrelatedWith(desc2)
   }
}
