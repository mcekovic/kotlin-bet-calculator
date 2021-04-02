package org.strangeforest.betcalculator.core

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

internal fun Assert<BetSettlementResult>.currentReturn() = prop(BetSettlementResult::currentReturn)
internal fun Assert<BetSettlementResult>.maxReturn() = prop(BetSettlementResult::maxReturn)
internal fun Assert<BetSettlementResult>.state() = prop(BetSettlementResult::state)

internal fun Assert<BetSettlementResult>.hasCurrentReturn(currentReturn: String): Assert<BetSettlementResult> {
   currentReturn().isEqualByComparingTo(currentReturn)
   return this
}

internal fun Assert<BetSettlementResult>.hasMaxReturn(maxReturn: String): Assert<BetSettlementResult> {
   maxReturn().isEqualByComparingTo(maxReturn)
   return this
}

internal fun Assert<BetSettlementResult>.hasState(state: BetState): Assert<BetSettlementResult> {
   state().isEqualTo(state)
   return this
}
