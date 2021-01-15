package org.strangeforest.betcalculator.interrelation

data class IRDescriptor(
   val selectionId: Comparable<*>,
   val marketId: Comparable<*>,
   val eventId: Comparable<*>,
   val maxWinners: Int = 1,
   val tag: IRTag = IRTag.EMPTY,
   var noInterrelation: Boolean = false
) {

   companion object {
      val NO_IR = IRDescriptor(selectionId = false, marketId = false, eventId = false, noInterrelation = true)
   }
}
