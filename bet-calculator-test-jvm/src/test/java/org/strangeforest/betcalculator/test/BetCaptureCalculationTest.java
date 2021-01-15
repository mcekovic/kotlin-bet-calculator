package org.strangeforest.betcalculator.test;

import java.util.*;

import org.junit.jupiter.api.*;
import org.strangeforest.betcalculator.*;
import org.strangeforest.betcalculator.bettypes.*;
import org.strangeforest.betcalculator.interrelation.*;
import org.strangeforest.betcalculator.rules.*;
import org.strangeforest.betcalculator.util.*;

import static org.assertj.core.api.Assertions.*;

class BetCaptureCalculationTest {

	@Test
	void calculateSingleBet() {
		var bet = new Bet(Single.INSTANCE, "10", List.of(
			new BetLeg("2.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false)
		), List.of(), BetRules.Companion.getDEFAULT());

		var result = BetCaptureCalculator.INSTANCE.calculate(bet);

		assertThat(result.getMaxReturn()).isEqualByComparingTo(new Decimal("20"));
	}

	@Test
	void calculateAccumulatorBet() {
		var bet = new Bet(Accumulator.INSTANCE, "2", List.of(
			new BetLeg("2.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("3.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false)
		), List.of(), BetRules.Companion.getDEFAULT());

		var result = BetCaptureCalculator.INSTANCE.calculate(bet);

		assertThat(result.getMaxReturn()).isEqualByComparingTo(new Decimal("12"));
	}

	@Test
	void calculateDoublesBet() {
		var bet = new Bet(Doubles.INSTANCE, "1", List.of(
			new BetLeg("2.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("3.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("4.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false)
		), List.of(), BetRules.Companion.getDEFAULT());

		var result = BetCaptureCalculator.INSTANCE.calculate(bet);

		assertThat(result.getMaxReturn()).isEqualByComparingTo(new Decimal("26"));
	}

	@Test
	void calculateBigPermsBet() {
		var bet = new Bet(new Perms(10), "1", List.of(
			new BetLeg("1.1", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.2", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.4", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.6", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.8", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("2.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("3.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("4.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("5.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("6.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.1", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.2", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.4", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.6", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("1.8", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("2.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("3.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("4.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("5.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false),
			new BetLeg("6.0", LegStatus.Companion.getOPEN(), IRDescriptor.Companion.getNO_IR(), false)
		), List.of(), BetRules.Companion.getDEFAULT());

		var result = BetCaptureCalculator.INSTANCE.calculate(bet);

		assertThat(result.getMaxReturn()).isEqualByComparingTo(new Decimal("1662209157.7375774976"));
	}
}
