package org.strangeforest.betcalculator

import org.strangeforest.betcalculator.interrelation.*
import org.strangeforest.betcalculator.interrelation.IRType.*

sealed class BetCalculator<BR : BetResult<BR>> {

   private val irDetector = IRDetector()

   abstract fun unitResult(unit: BetUnit): BR

   fun calculate(bet: Bet): BR {
      val irDetector = IRDetector()
      return bet.units
         .filterNot(irDetector::checkInterrelationOrSkip)
         .map(::unitResult)
         .reduceOrNull { result1: BR, result2: BR -> result1 + result2 } ?: throw IRException(NO_VALID_UNITS, "No valid units")
   }
}

object BetCaptureCalculator : BetCalculator<BetCaptureResult>() {

   override fun unitResult(unit: BetUnit): BetCaptureResult = BetCaptureResult(unit.unitCount, unit.stake, unit.maxReturn, unit.state)
}

object BetSettlementCalculator : BetCalculator<BetSettlementResult>() {

   override fun unitResult(unit: BetUnit): BetSettlementResult = BetSettlementResult(unit.currentReturn, unit.maxReturn, unit.state)
}
