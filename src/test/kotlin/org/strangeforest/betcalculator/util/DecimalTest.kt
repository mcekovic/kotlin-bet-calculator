package org.strangeforest.betcalculator.util

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class DecimalTest {

   @Test
   fun plusTest() {
      assertThat("1".dec + "1".dec).isEqualTo("2".dec)
   }

   @Test
   fun minusTest() {
      assertThat("3".dec - "2".dec).isEqualTo("1".dec)
   }

   @Test
   fun timesTest() {
      assertThat("2".dec * "2".dec).isEqualTo("4".dec)
   }

   @Test
   fun divTest() {
      assertThat("6".dec / "3".dec).isEqualTo("2".dec)
   }

   @Test
   fun toDoubleTest() {
      assertThat("2".dec.toDouble()).isEqualTo(2.0)
   }

   @Test
   fun equalsTest() {
      assertThat(ONE).isEqualTo(ONE)
      assertThat(1.dec).isEqualTo(1.dec)
      assertThat(1.dec).isNotEqualTo(2.dec)
      assertThat(1.dec).isNotEqualTo(1)
   }

   @Test
   fun hashCodeTest() {
      assertThat(1.dec).hasHashCode(1.dec.hashCode())
   }

   @Test
   fun compareToTest() {
      assertTrue(2.dec > 1.dec)
   }

   @Test
   fun toStringTest() {
      assertThat(1.dec).hasToString("1")
   }
}