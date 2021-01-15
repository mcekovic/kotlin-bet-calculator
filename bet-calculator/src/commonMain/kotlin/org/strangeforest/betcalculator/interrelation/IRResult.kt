package org.strangeforest.betcalculator.interrelation

import org.strangeforest.betcalculator.interrelation.IRType.*

sealed class IRResult(val irType: IRType, val message: String) {

   fun isInterrelated(): Boolean = irType != NOT_INTERRELATED

   abstract fun toIRException(): IRException
}

object NotInterrelated : IRResult(NOT_INTERRELATED, "Not interrelated") {

   override fun toIRException(): IRException {
      throw IllegalStateException()
   }
}

class IRSelectionsResult(irType: IRType, val selectionId1: Comparable<*>, val selectionId2: Comparable<*>, reason: String) :
   IRResult(irType, "Selections $selectionId1 and $selectionId2 are interrelated: $reason") {

   override fun toIRException(): IRException {
      return IRSelectionsException(irType, selectionId1, selectionId2, message)
   }
}

class MaxWinnersViolationIRResult(val marketId: Comparable<*>, val selectionIds: Iterable<Comparable<*>>, val maxWinners: Int) :
   IRResult(MAX_WINNERS, "MaxWinners violation: More then $maxWinners Selections ($selectionIds) from Market $marketId are in the bet unit") {

   override fun toIRException(): IRException {
      return MaxWinnersViolationException(irType, marketId, selectionIds, maxWinners, message)
   }
}
