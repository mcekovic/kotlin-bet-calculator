package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IRTagTest {

   @Test
   fun ofTest() {
      val tag = IRTag.of("O:Score;D:Score,Rank;G:111,222;C:123,456,789>333")
      assertThat(IRTag.of(tag.toString())).isEqualTo(tag)
   }

   @Test
   fun outcomeMatchedWithTest() {
      val tag = IRTag.of("O:Score")
      assertTrue(tag outcomeMatchedWith IRTag.of("O:Score,Rank"))
      assertFalse(tag outcomeMatchedWith IRTag.of("O:Rank"))
   }

   @Test
   fun groupInterrelatedWithTest() {
      val tag = IRTag.of("O:Score;G:123")
      assertTrue(tag groupInterrelatedWith IRTag.of("O:Score,Rank;G:111,123"))
      assertFalse(tag groupInterrelatedWith IRTag.of("O:Rank;G:123"))
      assertFalse(tag groupInterrelatedWith IRTag.of("O:Score;G:456"))
   }

   @Test
   fun ccInterrelatedWithTest() {
      val tag = IRTag.of("O:Score;C:123>456")
      assertTrue(tag ccInterrelatedWith IRTag.of("D:Score,Rank;C:789>123"))
      assertFalse(tag ccInterrelatedWith IRTag.of("D:Score,Rank;C:789>456"))
      assertFalse(tag ccInterrelatedWith IRTag.of("D:Rank;C:789>123"))
   }

   @Test
   fun emptyTagTest() {
      assertThat(IRTag.EMPTY).hasToString("")
      assertThat(IRTag.of("")).isEqualTo(IRTag.EMPTY)
   }

   @Test
   fun plusTest() {
      val irTag1 = IRTag.of("O:Score;D:Corners,Cartons;G:1111,2222,3333,4444;C:1111,2222,3333>")
      val irTag2 = IRTag.of("O:Rank;D:Score,Rank,Cartons;G:9999,8888,7777,6666;C:>4444,5555,6666")
      val irTags12 = IRTag.of("O:Score,Rank;D:Score,Rank,Corners,Cartons;G:1111,2222,3333,4444,9999,8888,7777,6666;C:1111,2222,3333>4444,5555,6666")
      assertThat(irTag1 + irTag2).isEqualTo(irTags12)
      assertThat(irTag2 + irTag1).isEqualTo(irTags12)
   }
}
