package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.core.BetState.*
import org.strangeforest.betcalculator.util.*

internal interface BetResult<BR : BetResult<BR>> {

   operator fun plus(other: BR): BR
}

internal data class BetCaptureResult(
   val unitCount: Decimal,
   val stake: Decimal,
   val maxReturn: Decimal,
   val state: BetState = OPEN
) : BetResult<BetCaptureResult> {

   override operator fun plus(other: BetCaptureResult): BetCaptureResult = BetCaptureResult(
      unitCount + other.unitCount,
      stake + other.stake,
      maxReturn + other.maxReturn,
      state + other.state
   )
}

internal data class BetSettlementResult(
   val openUnitCount: Decimal,
   val wonUnitCount: Decimal,
   val voidUnitCount: Decimal,
   val lostUnitCount: Decimal,
   val currentReturn: Decimal,
   val maxReturn: Decimal,
   val state: BetState = OPEN
) : BetResult<BetSettlementResult> {

   val unitCount: Decimal
      get() = openUnitCount + wonUnitCount + voidUnitCount + lostUnitCount

   override operator fun plus(other: BetSettlementResult): BetSettlementResult = BetSettlementResult(
      openUnitCount + other.openUnitCount,
      wonUnitCount + other.wonUnitCount,
      voidUnitCount + other.voidUnitCount,
      lostUnitCount + other.lostUnitCount,
      currentReturn + other.currentReturn,
      maxReturn + other.maxReturn,
      state + other.state
   )
}
