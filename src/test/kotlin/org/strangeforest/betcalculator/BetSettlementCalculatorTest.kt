package org.strangeforest.betcalculator

import kotlin.test.*
import assertk.*
import org.strangeforest.betcalculator.BetState.*
import org.strangeforest.betcalculator.bettypes.*

class BetSettlementCalculatorTest {

   @Test
   fun wonSingeBetIsCalculated() {
      val bet = Bet(Single, "10", listOf(BetLeg("2", LegStatus.WON)))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).isResult("20", "20", WON)
   }

   @Test
   fun wonDoublesBetIsCalculated() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", LegStatus.LOST),
         BetLeg("3", LegStatus.VOID),
         BetLeg("4", LegStatus.WON)
      ))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).isResult("4", "4", WON)
   }

   @Test
   fun partiallyWonPatentBetWithOpenUnitsIsCalculated() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", LegStatus.WON),
         BetLeg("3", LegStatus.VOID),
         BetLeg("4", LegStatus.OPEN)
      ))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).isResult("5", "29", OPEN)
   }
}
