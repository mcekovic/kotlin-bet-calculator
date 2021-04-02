package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.interrelation.*
import org.strangeforest.betcalculator.interrelation.IrType.*

internal sealed class BetCalculator<BR : BetResult<BR>> {

   private val irDetector = IrDetector()

   abstract fun unitResult(unit: BetUnit): BR

   fun calculate(bet: Bet): BR {
      val irDetector = IrDetector()
      return bet.units
         .filterNot(irDetector::checkInterrelationOrSkip)
         .map(::unitResult)
         .reduceOrNull { result1: BR, result2: BR -> result1 + result2 } ?: throw IrException(NO_VALID_UNITS, "No valid units")
   }
}

internal object BetCaptureCalculator : BetCalculator<BetCaptureResult>() {

   override fun unitResult(unit: BetUnit): BetCaptureResult = BetCaptureResult(unit.unitCount, unit.stake, unit.maxReturn, unit.state)
}

internal object BetSettlementCalculator : BetCalculator<BetSettlementResult>() {

   override fun unitResult(unit: BetUnit): BetSettlementResult = BetSettlementResult(
      unit.openUnitCount, unit.wonUnitCount, unit.voidUnitCount, unit.lostUnitCount,
      unit.currentReturn, unit.maxReturn, unit.state
   )
}
