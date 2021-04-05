package org.strangeforest.betcalculator.test;

import org.assertj.core.api.AbstractAssert;
import org.strangeforest.betcalculator.BetCaptureResult;

import java.util.Objects;

public class BetCaptureResultAssert extends AbstractAssert<BetCaptureResultAssert, BetCaptureResult> {

    protected BetCaptureResultAssert(BetCaptureResult result) {
        super(result, BetCaptureResultAssert.class);
    }

    public BetCaptureResultAssert hasUnitCount(String unitCount) {
        isNotNull();
        if (!Objects.equals(actual.getUnitCount(), unitCount))
            failWithMessage("Expected unit count to be <%1$s> but was <%2$s>", unitCount, actual.getUnitCount());
        return this;
    }

    public BetCaptureResultAssert hasMaxReturn(String maxReturn) {
        isNotNull();
        if (!Objects.equals(actual.getMaxReturn(), maxReturn))
            failWithMessage("Expected max return to be <%1$s> but was <%2$s>", maxReturn, actual.getMaxReturn());
        return this;
    }
}
