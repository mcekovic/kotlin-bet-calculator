package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import java.math.*
import assertk.*
import assertk.assertions.*

class AccumulatorTest {

   @Test
   fun toStringTest() {
      assertThat(AccumulatorN(5)).hasToString("Accumulator5")
      assertThat(Single).hasToString("Single")
      assertThat(Accumulator).hasToString("Accumulator")
   }

   @Test
   fun invalidAccumulatorTest() {
      assertFails { AccumulatorN(0) }
      assertFails { EachWayAccumulatorN(5, BigDecimal("-1")) }
   }
}
