package org.strangeforest.betcalculator.interrelation

internal data class IrDescriptor(
   val selectionId: Any,
   val marketId: Any,
   val eventId: Any,
   val maxWinners: Int? = 1,
   val tag: IrTag = IrTag.EMPTY
) {

   companion object {
      fun noIr() = IrDescriptor(selectionId = Any(), marketId = Any(), eventId = Any())
   }
}
