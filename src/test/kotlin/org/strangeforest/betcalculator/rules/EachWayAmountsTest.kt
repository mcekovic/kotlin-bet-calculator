package org.strangeforest.betcalculator.rules

import kotlin.test.*
import java.math.*
import assertk.*
import assertk.assertions.*

class EachWayAmountsTest {

   @Test
   fun plusTest() {
      assertThat(EachWayAmounts("1", "2") + EachWayAmounts("3", "4")).isEqualTo(EachWayAmounts("4", "6"))
   }

   @Test
   fun timesTest() {
      assertThat(EachWayAmounts("1", "2") * EachWayAmounts("3", "4")).isEqualTo(EachWayAmounts("3", "8"))
   }

   @Test
   fun totalTest() {
      assertThat(EachWayAmounts("4", "2").total).isEqualByComparingTo("6")
      assertThat(EachWayAmounts.EW_ZERO.total).isEqualByComparingTo("0")
   }

   @Test
   fun equallyDividedTest() {
      assertThat(EachWayAmounts("4", "2").equallyDivided).isEqualTo(EachWayAmounts("3", "3"))
   }

   @Test
   fun winPrecedenceTest() {
      assertThat(EachWayAmounts("2", "2").winPrecedence(BigDecimal("3"))).isEqualTo(EachWayAmounts("3", "1"))
      assertThat(EachWayAmounts("2", "2").winPrecedence(BigDecimal("5"))).isEqualTo(EachWayAmounts("4", "0"))
   }
}
