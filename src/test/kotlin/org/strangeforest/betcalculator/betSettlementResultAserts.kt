package org.strangeforest.betcalculator

import java.math.*
import assertk.*
import assertk.assertions.*

fun Assert<BetSettlementResult>.currentReturn() = prop(BetSettlementResult::currentReturn)
fun Assert<BetSettlementResult>.maxReturn() = prop(BetSettlementResult::maxReturn)
fun Assert<BetSettlementResult>.state() = prop(BetSettlementResult::state)

fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String) {
   currentReturn().isEqualByComparingTo(BigDecimal(currentReturn))
   maxReturn().isEqualByComparingTo(BigDecimal(maxReturn))
}

fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String, state: BetState) {
   isResult(currentReturn, maxReturn)
   state().isEqualTo(state)
}
