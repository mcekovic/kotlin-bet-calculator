package org.strangeforest.betcalculator

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.rules.EachWayType.*
import org.strangeforest.betcalculator.util.*

class LegStatusTest {

   @Test
   fun constructionTest() {
      assertTrue(LegStatus.OPEN.isOpen())
      assertFalse(LegStatus.OPEN.resulted)
      assertTrue(LegStatus.WON.isWon())
      assertTrue(LegStatus.WON.resulted)
      assertFalse(LegStatus.WON.isOpen())
      assertTrue(LegStatus.VOID.isVoid())
      assertFalse(LegStatus.VOID.isLost())
      assertFalse(LegStatus.VOID.isWon())
      assertTrue(LegStatus.LOST.isLost())
      assertFalse(LegStatus.LOST.isVoid())
      assertThat(LegStatus.WON.winFactor).isEqualByComparingTo("1")
      assertThat(LegStatus.VOID.voidFactor).isEqualByComparingTo("1")
      assertTrue(LegStatus.resulted("0.5", "0").isWon())
      assertTrue(LegStatus.resulted("0.5", "0.5").isWon())
      assertTrue(LegStatus.resulted("0", "0.5").isVoid())

      val minusOne = Decimal("-1")
      assertFails { LegStatus.open(minusOne) }
      assertFails { LegStatus.open(minusOne) }
      assertFails { LegStatus.resulted(minusOne, ZERO) }
      assertFails { LegStatus.resulted(ZERO, minusOne) }
   }

   @Test
   fun factoredPriceTest() {
      assertThat(LegStatus.OPEN.factoredPrice(Decimal("2"))).isEqualByComparingTo("2")
      assertThat(LegStatus.WON.factoredPrice(Decimal("2"))).isEqualByComparingTo("2")
      assertThat(LegStatus.VOID.factoredPrice(Decimal("2"))).isEqualByComparingTo("1")
      assertThat(LegStatus.LOST.factoredPrice(Decimal("2"))).isEqualByComparingTo("0")

      assertThat(LegStatus.resulted("0.5", "0").factoredPrice(Decimal("2"))).isEqualByComparingTo("1")
      assertThat(LegStatus.resulted("0.5", "0", "0.25").factoredPrice(Decimal("2"), PLACE)).isEqualByComparingTo("0.625")

      assertThat(LegStatus.resulted("0.5", "0.5").factoredPrice(Decimal("2"))).isEqualByComparingTo("1.5")
      assertThat(LegStatus.resulted("0.5", "0.5", "0.25").factoredPrice(Decimal("2"), PLACE)).isEqualByComparingTo("1.125")

      assertThat(LegStatus.resulted("0", "0.5").factoredPrice(Decimal("2"))).isEqualByComparingTo("0.5")
      assertThat(LegStatus.resulted("0", "0.5", "0.25").factoredPrice(Decimal("2"), PLACE)).isEqualByComparingTo("0.5")
      
      assertFails { LegStatus.OPEN.factoredPrice(Decimal("2"), EACH_WAY) }
   }
}
