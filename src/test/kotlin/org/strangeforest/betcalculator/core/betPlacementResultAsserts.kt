package org.strangeforest.betcalculator.core

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

internal fun Assert<BetCaptureResult>.unitCount() = prop(BetCaptureResult::unitCount)
internal fun Assert<BetCaptureResult>.stake() = prop(BetCaptureResult::stake)
internal fun Assert<BetCaptureResult>.maxReturn() = prop(BetCaptureResult::maxReturn)

internal fun Assert<BetCaptureResult>.isResult(unitCount: Int, stake: String, maxReturn: String) {
   unitCount().isEqualByComparingTo(unitCount.dec)
   stake().isEqualByComparingTo(stake)
   maxReturn().isEqualByComparingTo(maxReturn)
}
