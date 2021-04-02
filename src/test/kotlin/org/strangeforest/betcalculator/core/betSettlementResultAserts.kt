package org.strangeforest.betcalculator.core

import assertk.*
import assertk.assertions.*
import org.strangeforest.betcalculator.util.*

internal fun Assert<BetSettlementResult>.currentReturn() = prop(BetSettlementResult::currentReturn)
internal fun Assert<BetSettlementResult>.maxReturn() = prop(BetSettlementResult::maxReturn)
internal fun Assert<BetSettlementResult>.state() = prop(BetSettlementResult::state)
internal fun Assert<BetSettlementResult>.wonUnitCount() = prop(BetSettlementResult::wonUnitCount)
internal fun Assert<BetSettlementResult>.voidUnitCount() = prop(BetSettlementResult::voidUnitCount)
internal fun Assert<BetSettlementResult>.lostUnitCount() = prop(BetSettlementResult::lostUnitCount)

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

internal fun Assert<BetSettlementResult>.hasWonUnitCount(wonUnitCount: Int): Assert<BetSettlementResult> {
   wonUnitCount().isEqualByComparingTo(wonUnitCount.dec)
   return this
}

internal fun Assert<BetSettlementResult>.hasVoidUnitCount(voidUnitCount: Int): Assert<BetSettlementResult> {
   voidUnitCount().isEqualByComparingTo(voidUnitCount.dec)
   return this
}

internal fun Assert<BetSettlementResult>.hasLostUnitCount(lostUnitCount: Int): Assert<BetSettlementResult> {
   lostUnitCount().isEqualByComparingTo(lostUnitCount.dec)
   return this
}
