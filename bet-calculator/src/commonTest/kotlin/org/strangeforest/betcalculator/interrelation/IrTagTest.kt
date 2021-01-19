package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class IrTagTest {

   @Test
   fun ofTest() {
      val tag = IrTag.of("O:Score;D:Score,Rank;G:111,222;C:123,456,789>333")
      assertThat(IrTag.of(tag.toString())).isEqualTo(tag)
   }

   @Test
   fun outcomeMatchedWithTest() {
      val tag = IrTag.of("O:Score")
      assertTrue(tag outcomeMatchedWith IrTag.of("O:Score,Rank"))
      assertFalse(tag outcomeMatchedWith IrTag.of("O:Rank"))
   }

   @Test
   fun groupInterrelatedWithTest() {
      val tag = IrTag.of("O:Score;G:123")
      assertTrue(tag groupInterrelatedWith IrTag.of("O:Score,Rank;G:111,123"))
      assertFalse(tag groupInterrelatedWith IrTag.of("O:Rank;G:123"))
      assertFalse(tag groupInterrelatedWith IrTag.of("O:Score;G:456"))
   }

   @Test
   fun ccInterrelatedWithTest() {
      val tag = IrTag.of("O:Score;C:123>456")
      assertTrue(tag ccInterrelatedWith IrTag.of("D:Score,Rank;C:789>123"))
      assertFalse(tag ccInterrelatedWith IrTag.of("D:Score,Rank;C:789>456"))
      assertFalse(tag ccInterrelatedWith IrTag.of("D:Rank;C:789>123"))
   }

   @Test
   fun emptyTagTest() {
      assertThat(IrTag.EMPTY).hasToString("")
      assertThat(IrTag.of("")).isEqualTo(IrTag.EMPTY)
   }

   @Test
   fun plusTest() {
      val irTag1 = IrTag.of("O:Score;D:Corners,Cartons;G:1111,2222,3333,4444;C:1111,2222,3333>")
      val irTag2 = IrTag.of("O:Rank;D:Score,Rank,Cartons;G:9999,8888,7777,6666;C:>4444,5555,6666")
      val irTags12 = IrTag.of("O:Score,Rank;D:Score,Rank,Corners,Cartons;G:1111,2222,3333,4444,9999,8888,7777,6666;C:1111,2222,3333>4444,5555,6666")
      assertThat(irTag1 + irTag2).isEqualTo(irTags12)
      assertThat(irTag2 + irTag1).isEqualTo(irTags12)
   }

   @Test
   fun invalidTagTest() {
      assertFailsWith<IllegalArgumentException> { IrTag.of("X:Score") }
      assertFailsWith<IllegalArgumentException> { IrTag.of("Score") }
      assertFailsWith<IllegalArgumentException> { IrTag.of("C:1111,2222,3333") }
   }
}
