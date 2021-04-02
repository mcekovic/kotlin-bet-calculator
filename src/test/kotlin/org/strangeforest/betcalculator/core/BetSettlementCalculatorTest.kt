package org.strangeforest.betcalculator.core

import kotlin.test.*
import assertk.*
import org.strangeforest.betcalculator.core.BetState.*
import org.strangeforest.betcalculator.bettypes.*

class BetSettlementCalculatorTest {

   @Test
   fun wonSingeBetIsCalculated() {
      val bet = Bet(Single, "10", listOf(BetLeg("2", LegStatus.WON)))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).hasCurrentReturn("20").hasMaxReturn("20").hasState(WON)
         .hasWonUnitCount(1).hasVoidUnitCount(0).hasLostUnitCount(0)
   }

   @Test
   fun wonDoublesBetIsCalculated() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", LegStatus.LOST),
         BetLeg("3", LegStatus.VOID),
         BetLeg("4", LegStatus.WON)
      ))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).hasCurrentReturn("4").hasMaxReturn("4").hasState(WON)
         .hasWonUnitCount(1).hasVoidUnitCount(0).hasLostUnitCount(2)
   }

   @Test
   fun partiallyVoidDoublesBetIsCalculated() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", LegStatus.LOST),
         BetLeg("3", LegStatus.VOID),
         BetLeg("4", LegStatus.VOID)
      ))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).hasCurrentReturn("1").hasMaxReturn("1").hasState(VOID)
         .hasWonUnitCount(0).hasVoidUnitCount(1).hasLostUnitCount(2)
   }

   @Test
   fun partiallyWonPatentBetWithOpenUnitsIsCalculated() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", LegStatus.WON),
         BetLeg("3", LegStatus.VOID),
         BetLeg("4", LegStatus.OPEN)
      ))

      val result = BetSettlementCalculator.calculate(bet)

      assertThat(result).hasCurrentReturn("5").hasMaxReturn("29").hasState(OPEN)
         .hasWonUnitCount(2).hasVoidUnitCount(1).hasLostUnitCount(0)
   }
}
