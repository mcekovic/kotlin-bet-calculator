package org.strangeforest.betcalculator.core

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

internal fun Assert<BetCaptureResult>.unitCount() = prop(BetCaptureResult::unitCount)
internal fun Assert<BetCaptureResult>.stake() = prop(BetCaptureResult::stake)
internal fun Assert<BetCaptureResult>.maxReturn() = prop(BetCaptureResult::maxReturn)

internal fun Assert<BetCaptureResult>.hasUnitCount(unitCount: Int): Assert<BetCaptureResult> {
   unitCount().isEqualByComparingTo(unitCount.dec)
   return this
}

internal fun Assert<BetCaptureResult>.hasStake(stake: String): Assert<BetCaptureResult> {
   stake().isEqualByComparingTo(stake)
   return this
}

internal fun Assert<BetCaptureResult>.hasMaxReturn(maxReturn: String): Assert<BetCaptureResult> {
   maxReturn().isEqualByComparingTo(maxReturn)
   return this
}
