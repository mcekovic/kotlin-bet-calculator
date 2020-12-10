package org.strangeforest.betcalculator

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

fun Assert<BetCaptureResult>.unitCount() = prop(BetCaptureResult::unitCount)
fun Assert<BetCaptureResult>.stake() = prop(BetCaptureResult::stake)
fun Assert<BetCaptureResult>.maxReturn() = prop(BetCaptureResult::maxReturn)

fun Assert<BetCaptureResult>.isResult(unitCount: Int, stake: String, maxReturn: String) {
   unitCount().isEqualByComparingTo(unitCount.dec)
   stake().isEqualByComparingTo(stake)
   maxReturn().isEqualByComparingTo(maxReturn)
}
