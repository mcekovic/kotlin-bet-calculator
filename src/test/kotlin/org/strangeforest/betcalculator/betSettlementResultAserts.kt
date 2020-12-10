package org.strangeforest.betcalculator

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

fun Assert<BetSettlementResult>.currentReturn() = prop(BetSettlementResult::currentReturn)
fun Assert<BetSettlementResult>.maxReturn() = prop(BetSettlementResult::maxReturn)
fun Assert<BetSettlementResult>.state() = prop(BetSettlementResult::state)

fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String) {
   currentReturn().isEqualByComparingTo(currentReturn)
   maxReturn().isEqualByComparingTo(maxReturn)
}

fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String, state: BetState) {
   isResult(currentReturn, maxReturn)
   state().isEqualTo(state)
}
