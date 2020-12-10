package org.strangeforest.betcalculator.util

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class CombinationsTest {

   @Test
   fun combinationsTest() {
      assertThat(combinations(listOf("A", "B", "C"), 2).toList()).isEqualTo(listOf(
         listOf("A", "B"),
         listOf("A", "C"),
         listOf("B", "C")
      ))
   }

   @Test
   fun emptyItemsTest() {
      assertThat(combinations(listOf<String>(), 0).toList()).isEmpty()
   }

   @Test
   fun invalidCombinations() {
      assertFailsWith<IllegalArgumentException> {
         Combinations(listOf("A", "B"), -1)
      }
      assertFailsWith<IllegalArgumentException> {
         Combinations(listOf("A", "B"), 3)
      }
   }
}
