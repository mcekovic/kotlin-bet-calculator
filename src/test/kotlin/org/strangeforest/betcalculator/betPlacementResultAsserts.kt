package org.strangeforest.betcalculator

import java.math.*
import assertk.*
import assertk.assertions.*

fun Assert<BetCaptureResult>.unitCount() = prop(BetCaptureResult::unitCount)
fun Assert<BetCaptureResult>.stake() = prop(BetCaptureResult::stake)
fun Assert<BetCaptureResult>.maxReturn() = prop(BetCaptureResult::maxReturn)

fun Assert<BetCaptureResult>.isResult(unitCount: Int, stake: String, maxReturn: String) {
   unitCount().isEqualByComparingTo(BigDecimal(unitCount))
   stake().isEqualByComparingTo(BigDecimal(stake))
   maxReturn().isEqualByComparingTo(BigDecimal(maxReturn))
}
