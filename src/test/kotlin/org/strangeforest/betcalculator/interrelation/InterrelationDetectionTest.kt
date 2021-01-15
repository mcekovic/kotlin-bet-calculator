package org.strangeforest.betcalculator.interrelation

import kotlin.test.*
import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.*
import org.strangeforest.betcalculator.bettypes.*

class InterrelationDetectionTest {

   @Test
   fun interrelatedSelectionsAreDetected() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(121, 12, 1))
      ))

	   assertFailsWith<IRSelectionsException> {
		   BetCaptureCalculator.calculate(bet)
	   }
   }

   @Test
   fun maxWinnersViolationIsDetected() {
      val bet = Bet(Patent, "1", listOf(
         BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1, 2)),
         BetLeg("3", irDescriptor = IRDescriptor(112, 11, 1, 2)),
         BetLeg("4", irDescriptor = IRDescriptor(113, 11, 1, 2))
      ))

	   assertFailsWith<MaxWinnersViolationException> {
	      BetCaptureCalculator.calculate(bet)
	   }
   }

   @Test
   fun unitsWithInterrelatedSelectionsAreSkipped() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(121, 12, 1))
      ))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).isResult(2, "2", "20")
   }

   @Test
   fun betWithAllUnitsWithInterrelatedSelectionsIsDetected() {
      val bet = Bet(Doubles, "1", listOf(
         BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("3", irDescriptor = IRDescriptor(111, 11, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(112, 11, 1))
      ))

	   assertFailsWith<IRException> {
		   BetCaptureCalculator.calculate(bet)
	   }
   }

	@Test
	fun interrelatedSelectionsAreIgnored() {
		val bet = Bet(StrictVariablePerms(2, 3), "1", listOf(
			BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1, noInterrelation = true)),
			BetLeg("3", irDescriptor = IRDescriptor(112, 11, 1, noInterrelation = true)),
			BetLeg("4", irDescriptor = IRDescriptor(113, 11, 1))
		))

      BetCaptureCalculator.calculate(bet)
	}

	@Test
   fun allInterrelationsAreReturned() {
		val legs = listOf(
         BetLeg("2", irDescriptor = IRDescriptor(111, 11, 1, 2)),
         BetLeg("3", irDescriptor = IRDescriptor(112, 11, 1, 2)),
         BetLeg("4", irDescriptor = IRDescriptor(113, 11, 1, 2)),
         BetLeg("4", irDescriptor = IRDescriptor(121, 12, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(122, 12, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(122, 12, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(131, 13, 1)),
         BetLeg("4", irDescriptor = IRDescriptor(141, 14, 1))
      )

		val results = IRDetector.getInterrelations(legs)
		assertThat(results).hasSize(4)
	}
}
