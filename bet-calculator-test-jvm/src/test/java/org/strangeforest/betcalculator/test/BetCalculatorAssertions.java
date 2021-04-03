package org.strangeforest.betcalculator.test;

import org.assertj.core.api.Assertions;
import org.strangeforest.betcalculator.BetCaptureResult;

public class BetCalculatorAssertions extends Assertions {

    public static BetCaptureResultAssert assertThat(BetCaptureResult actual) {
        return new BetCaptureResultAssert(actual);
    }
}
