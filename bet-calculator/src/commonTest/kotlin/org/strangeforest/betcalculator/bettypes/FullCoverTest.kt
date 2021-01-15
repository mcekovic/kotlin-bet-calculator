package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class FullCoverTest {

   @Test
   fun toStringTest() {
      assertThat(FullCoverN(4)).hasToString("FullCover4")
      assertThat(FullCoverWithSinglesN(4)).hasToString("FullCoverWithSingles4")
      assertThat(Patent).hasToString("Patent")
   }
}
