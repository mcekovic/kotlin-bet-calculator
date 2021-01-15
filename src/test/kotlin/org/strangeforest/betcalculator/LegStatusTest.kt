package org.strangeforest.betcalculator

import kotlin.test.*
import assertk.*
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

      val minusOne = "-1".dec
      assertFails { LegStatus.open(minusOne) }
      assertFails { LegStatus.open(minusOne) }
      assertFails { LegStatus.resulted(minusOne, ZERO) }
      assertFails { LegStatus.resulted(ZERO, minusOne) }
   }

   @Test
   fun factoredPriceTest() {
      assertThat(LegStatus.OPEN.factoredPrice("2".dec)).isEqualByComparingTo("2")
      assertThat(LegStatus.WON.factoredPrice("2".dec)).isEqualByComparingTo("2")
      assertThat(LegStatus.VOID.factoredPrice("2".dec)).isEqualByComparingTo("1")
      assertThat(LegStatus.LOST.factoredPrice("2".dec)).isEqualByComparingTo("0")

      assertThat(LegStatus.resulted("0.5", "0").factoredPrice("2".dec)).isEqualByComparingTo("1")
      assertThat(LegStatus.resulted("0.5", "0", "0.25").factoredPrice("2".dec, PLACE)).isEqualByComparingTo("0.625")

      assertThat(LegStatus.resulted("0.5", "0.5").factoredPrice("2".dec)).isEqualByComparingTo("1.5")
      assertThat(LegStatus.resulted("0.5", "0.5", "0.25").factoredPrice("2".dec, PLACE)).isEqualByComparingTo("1.125")

      assertThat(LegStatus.resulted("0", "0.5").factoredPrice("2".dec)).isEqualByComparingTo("0.5")
      assertThat(LegStatus.resulted("0", "0.5", "0.25").factoredPrice("2".dec, PLACE)).isEqualByComparingTo("0.5")
      
      assertFails { LegStatus.OPEN.factoredPrice("2".dec, EACH_WAY) }
   }
}
