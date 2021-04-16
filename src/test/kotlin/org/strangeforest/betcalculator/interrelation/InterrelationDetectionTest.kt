package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.bettypes.*
import org.strangeforest.betcalculator.core.*

class InterrelationDetectionTest {

   @Test
   fun interrelatedSelectionsAreDetected() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(121, 12, 1))
      ))

      assertFailsWith<IrSelectionsException> {
         BetCaptureCalculator.calculate(bet)
      }
   }

   @Test
   fun maxWinnersViolationIsDetected() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", irDescriptor = IrDescriptor(111, 11, 1, 2)),
         BetLeg("3", irDescriptor = IrDescriptor(112, 11, 1, 2)),
         BetLeg("4", irDescriptor = IrDescriptor(113, 11, 1, 2))
      ))

      assertFailsWith<MaxWinnersViolationException> {
         BetCaptureCalculator.calculate(bet)
      }
   }

   @Test
   fun unitsWithInterrelatedSelectionsAreSkipped() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(121, 12, 1))
      ))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(2).hasStake("2").hasMaxReturn("20")
   }

   @Test
   fun betWithAllUnitsWithInterrelatedSelectionsIsDetected() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IrDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(112, 11, 1))
      ))

      assertFailsWith<IrException> {
         BetCaptureCalculator.calculate(bet)
      }
   }

   @Test
   fun interrelatedSelectionsAreIgnored() {
      val bet = Bet(StrictVariablePerms(2, 3), "1", listOf(
         BetLeg("2", irDescriptor = IrDescriptor.noIr()),
         BetLeg("3", irDescriptor = IrDescriptor.noIr()),
         BetLeg("4", irDescriptor = IrDescriptor(113, 11, 1))
      ))

      BetCaptureCalculator.calculate(bet)
   }

   @Test
   fun allInterrelationsAreReturned() {
      val legs = listOf(
         BetLeg("2", irDescriptor = IrDescriptor(111, 11, 1, 2)),
         BetLeg("3", irDescriptor = IrDescriptor(112, 11, 1, 2)),
         BetLeg("4", irDescriptor = IrDescriptor(113, 11, 1, 2)),
         BetLeg("4", irDescriptor = IrDescriptor(121, 12, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(122, 12, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(122, 12, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(131, 13, 1)),
         BetLeg("4", irDescriptor = IrDescriptor(141, 14, 1))
      )

      val results = IrDetector.getInterrelations(legs)
      assertThat(results).hasSize(4)
   }
}
