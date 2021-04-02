package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IrTagSetTest {

   @Test
   fun intersectsTest() {
      assertTrue(IrTagSet("1", "2") intersects IrTagSet("2", "3"))
      assertFalse(IrTagSet("1", "2") intersects IrTagSet("3", "4"))
      assertTrue(IrTagSet("*") intersects IrTagSet("1"))
      assertTrue(IrTagSet("1", "2") intersects IrTagSet("*"))
   }

   @Test
   fun plusTest() {
      assertThat(IrTagSet("1", "2") + IrTagSet("3", "4")).isEqualTo(IrTagSet("1", "2", "3", "4"))
      assertThat(IrTagSet("1", "2") + IrTagSet("2", "3")).isEqualTo(IrTagSet("1", "2", "3"))
   }

   @Test
   fun emptyTest() {
      assertTrue(IrTagSet.EMPTY.empty)
      assertFalse(IrTagSet("123").empty)
   }

   @Test
   fun equalsTest() {
      assertThat(IrTagSet("1", "2")).isEqualTo(IrTagSet("2", "1"))
      assertThat(IrTagSet("1", "2")).isNotEqualTo(IrTagSet("1", "3"))
   }
}
