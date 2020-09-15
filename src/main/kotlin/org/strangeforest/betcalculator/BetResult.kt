package org.strangeforest.betcalculator

import java.math.*
import org.strangeforest.betcalculator.BetState.*

interface BetResult<BR : BetResult<BR>> {

   operator fun plus(other: BR): BR
}

data class BetCaptureResult(val unitCount: BigDecimal, val stake: BigDecimal, val maxReturn: BigDecimal, val state: BetState = OPEN) : BetResult<BetCaptureResult> {

   override operator fun plus(other: BetCaptureResult): BetCaptureResult = BetCaptureResult(
      unitCount + other.unitCount,
      stake + other.stake,
      maxReturn + other.maxReturn,
      state + other.state
   )
}

data class BetSettlementResult(val currentReturn: BigDecimal, val maxReturn: BigDecimal, val state: BetState = OPEN) : BetResult<BetSettlementResult> {

   override operator fun plus(other: BetSettlementResult): BetSettlementResult = BetSettlementResult(
      currentReturn + other.currentReturn,
      maxReturn + other.maxReturn,
      state + other.state
   )
}
