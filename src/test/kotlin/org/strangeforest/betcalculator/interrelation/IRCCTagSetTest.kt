package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IRCCTagSetTest {

   @Test
   fun intersectsTest() {
      assertTrue(IRCCTagSet(IRTagSet("1", "2"), IRTagSet.EMPTY) ccIntersects  IRCCTagSet(IRTagSet.EMPTY, IRTagSet("2", "3")))
      assertFalse(IRCCTagSet(IRTagSet("1", "2"), IRTagSet.EMPTY) ccIntersects IRCCTagSet(IRTagSet.EMPTY, IRTagSet("3", "4")))
   }

   @Test
   fun plusTest() {
      assertThat(IRCCTagSet(IRTagSet("1", "2"), IRTagSet.EMPTY) + IRCCTagSet(IRTagSet("3", "4"), IRTagSet("5", "6"))).isEqualTo(IRCCTagSet(IRTagSet("1", "2", "3", "4"), IRTagSet("5", "6")))
   }

   @Test
   fun emptyTest() {
      assertTrue(IRCCTagSet.EMPTY.empty)
      assertFalse(IRCCTagSet(IRTagSet("1", "2"), IRTagSet.EMPTY).empty)
      assertFalse(IRCCTagSet(IRTagSet("1", "2"), IRTagSet("3")).empty)
   }

   @Test
   fun equalsTest() {
      assertThat(IRCCTagSet(IRTagSet("1", "2"), IRTagSet.EMPTY)).isEqualTo(IRCCTagSet(IRTagSet("2", "1"), IRTagSet.EMPTY))
      assertThat(IRCCTagSet(IRTagSet("1", "2"), IRTagSet("3"))).isNotEqualTo(IRCCTagSet(IRTagSet("1"), IRTagSet("2", "3")))
   }
}