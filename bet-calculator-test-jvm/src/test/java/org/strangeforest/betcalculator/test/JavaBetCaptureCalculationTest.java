package org.strangeforest.betcalculator.test;

import org.junit.jupiter.api.*;
import org.strangeforest.betcalculator.*;

import static org.assertj.core.api.Assertions.*;
import static org.strangeforest.betcalculator.BetCalculatorKt.*;

class JavaBetCaptureCalculationTest {

	@Test
	void calculateSingleBet() {
		var bet = new Bet("Single", "10", new BetLeg[] {
			new BetLeg("2.0")
		});

		var result = calculateCapture(bet);

		assertThat(result.getMaxReturn()).isEqualTo("20.0");
	}

	@Test
	void calculateAccumulatorBet() {
		var bet = new Bet("Accumulator", "2", new BetLeg[] {
			new BetLeg("2.0"),
			new BetLeg("3.0")
		});

		var result = calculateCapture(bet);

		assertThat(result.getMaxReturn()).isEqualTo("12.00");
	}

	@Test
	void calculateDoublesBet() {
		var bet = new Bet("Doubles", "1", new BetLeg[] {
			new BetLeg("2.0"),
			new BetLeg("3.0"),
			new BetLeg("4.0")
		});

		var result = calculateCapture(bet);

		assertThat(result.getMaxReturn()).isEqualTo("26.00");
	}

	@Test
	void calculateBigPermsBet() {
		var bet = new Bet("Perms10", "1", new BetLeg[] {
			new BetLeg("1.1"),
			new BetLeg("1.2"),
			new BetLeg("1.4"),
			new BetLeg("1.6"),
			new BetLeg("1.8"),
			new BetLeg("2.0"),
			new BetLeg("3.0"),
			new BetLeg("4.0"),
			new BetLeg("5.0"),
			new BetLeg("6.0"),
			new BetLeg("1.1"),
			new BetLeg("1.2"),
			new BetLeg("1.4"),
			new BetLeg("1.6"),
			new BetLeg("1.8"),
			new BetLeg("2.0"),
			new BetLeg("3.0"),
			new BetLeg("4.0"),
			new BetLeg("5.0"),
			new BetLeg("6.0")
		});

		var result = calculateCapture(bet);

		assertThat(result.getMaxReturn()).isEqualTo("1662209157.7375774976");
	}
}
