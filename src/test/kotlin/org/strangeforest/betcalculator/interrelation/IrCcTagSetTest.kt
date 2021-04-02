package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IrCcTagSetTest {

   @Test
   fun intersectsTest() {
      assertTrue(IrCcTagSet(IrTagSet("1", "2"), IrTagSet.EMPTY) ccIntersects  IrCcTagSet(IrTagSet.EMPTY, IrTagSet("2", "3")))
      assertFalse(IrCcTagSet(IrTagSet("1", "2"), IrTagSet.EMPTY) ccIntersects IrCcTagSet(IrTagSet.EMPTY, IrTagSet("3", "4")))
   }

   @Test
   fun plusTest() {
      assertThat(IrCcTagSet(IrTagSet("1", "2"), IrTagSet.EMPTY) + IrCcTagSet(IrTagSet("3", "4"), IrTagSet("5", "6"))).isEqualTo(IrCcTagSet(IrTagSet("1", "2", "3", "4"), IrTagSet("5", "6")))
   }

   @Test
   fun emptyTest() {
      assertTrue(IrCcTagSet.EMPTY.empty)
      assertFalse(IrCcTagSet(IrTagSet("1", "2"), IrTagSet.EMPTY).empty)
      assertFalse(IrCcTagSet(IrTagSet.EMPTY, IrTagSet("1",), ).empty)
      assertFalse(IrCcTagSet(IrTagSet("1"), IrTagSet("3")).empty)
   }

   @Test
   fun equalsTest() {
      assertThat(IrCcTagSet(IrTagSet("1", "2"), IrTagSet.EMPTY)).isEqualTo(IrCcTagSet(IrTagSet("2", "1"), IrTagSet.EMPTY))
      assertThat(IrCcTagSet(IrTagSet("1", "2"), IrTagSet("3"))).isNotEqualTo(IrCcTagSet(IrTagSet("1"), IrTagSet("2", "3")))
   }
}
