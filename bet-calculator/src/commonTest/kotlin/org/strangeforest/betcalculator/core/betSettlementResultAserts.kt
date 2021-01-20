package org.strangeforest.betcalculator.core

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

internal fun Assert<BetSettlementResult>.currentReturn() = prop(BetSettlementResult::currentReturn)
internal fun Assert<BetSettlementResult>.maxReturn() = prop(BetSettlementResult::maxReturn)
internal fun Assert<BetSettlementResult>.state() = prop(BetSettlementResult::state)

internal fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String) {
   currentReturn().isEqualByComparingTo(currentReturn)
   maxReturn().isEqualByComparingTo(maxReturn)
}

internal fun Assert<BetSettlementResult>.isResult(currentReturn: String, maxReturn: String, state: BetState) {
   isResult(currentReturn, maxReturn)
   state().isEqualTo(state)
}
