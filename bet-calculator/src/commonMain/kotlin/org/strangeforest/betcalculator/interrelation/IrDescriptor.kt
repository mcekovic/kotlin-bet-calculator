package org.strangeforest.betcalculator.interrelation

internal data class IrDescriptor(
   val selectionId: Comparable<*>,
   val marketId: Comparable<*>,
   val eventId: Comparable<*>,
   val maxWinners: Int? = 1,
   val tag: IrTag = IrTag.EMPTY,
   var noInterrelation: Boolean = false
) {

   companion object {
      val NO_IR = IrDescriptor(selectionId = false, marketId = false, eventId = false, noInterrelation = true)
   }
}
