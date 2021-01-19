package org.strangeforest.betcalculator.interrelation

import org.strangeforest.betcalculator.interrelation.IrType.*

sealed class IrResult(val irType: IrType, val message: String) {

   fun isInterrelated(): Boolean = irType != NOT_INTERRELATED

   abstract fun toIRException(): IrException
}

object NotInterrelated : IrResult(NOT_INTERRELATED, "Not interrelated") {

   override fun toIRException(): IrException {
      throw IllegalStateException()
   }
}

class IrSelectionsResult(irType: IrType, val selectionId1: Comparable<*>, val selectionId2: Comparable<*>, reason: String) :
   IrResult(irType, "Selections $selectionId1 and $selectionId2 are interrelated: $reason") {

   override fun toIRException(): IrException {
      return IrSelectionsException(irType, selectionId1, selectionId2, message)
   }
}

class MaxWinnersViolationIrResult(val marketId: Comparable<*>, val selectionIds: Iterable<Comparable<*>>, val maxWinners: Int) :
   IrResult(MAX_WINNERS, "MaxWinners violation: More then $maxWinners Selections ($selectionIds) from Market $marketId are in the bet unit") {

   override fun toIRException(): IrException {
      return MaxWinnersViolationException(irType, marketId, selectionIds, maxWinners, message)
   }
}