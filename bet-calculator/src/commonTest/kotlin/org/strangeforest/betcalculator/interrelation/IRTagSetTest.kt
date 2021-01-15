package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IRTagSetTest {

   @Test
   fun intersectsTest() {
      assertTrue(IRTagSet("1", "2") intersects IRTagSet("2", "3"))
      assertFalse(IRTagSet("1", "2") intersects IRTagSet("3", "4"))
      assertTrue(IRTagSet("*") intersects IRTagSet("1"))
      assertTrue(IRTagSet("1", "2") intersects IRTagSet("*"))
   }

   @Test
   fun plusTest() {
      assertThat(IRTagSet("1", "2") + IRTagSet("3", "4")).isEqualTo(IRTagSet("1", "2", "3", "4"))
      assertThat(IRTagSet("1", "2") + IRTagSet("2", "3")).isEqualTo(IRTagSet("1", "2", "3"))
   }

   @Test
   fun emptyTest() {
      assertTrue(IRTagSet.EMPTY.empty)
      assertFalse(IRTagSet("123").empty)
   }

   @Test
   fun equalsTest() {
      assertThat(IRTagSet("1", "2")).isEqualTo(IRTagSet("2", "1"))
      assertThat(IRTagSet("1", "2")).isNotEqualTo(IRTagSet("1", "3"))
   }
}
