package org.strangeforest.betcalculator.rules

import kotlin.test.*

class PlaceTermsTest {

   @Test
   fun winOnlyTest() {
      assertTrue(PlaceTerms.WIN_ONLY.winOnly)
      assertFalse(PlaceTerms(4, "5").winOnly)
      assertFalse(PlaceTerms(3, "1").winOnly)
      assertFalse(PlaceTerms(1, "2").winOnly)
   }

   @Test
   fun placeOnlyTest() {
      assertTrue(PlaceTerms(3, "1").placeOnly)
      assertFalse(PlaceTerms(3, "4").placeOnly)
      assertFalse(PlaceTerms.WIN_ONLY.placeOnly)
   }

   @Test
   fun invalidPlaceTermsTest() {
      assertFails { PlaceTerms(0, "2") }
      assertFails { PlaceTerms(3, "0") }
   }
}
