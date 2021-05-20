package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.interrelation.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayType.*
import org.strangeforest.betcalculator.util.*

internal data class BetLeg(
   val price: Decimal,
   val status: LegStatus = LegStatus.OPEN,
   val irDescriptor: IrDescriptor = IrDescriptor.noIr(),
   override val banker: Boolean = false
) : BankerAware {

   init {
      require(price >= ONE) { "price must be greater than or equal to 1" }
   }

   constructor(price: String, status: LegStatus = LegStatus.OPEN, irDescriptor: IrDescriptor = IrDescriptor.noIr(), banker: Boolean = false) :
      this(price.dec, status, irDescriptor, banker)

   fun factoredPrice(eachWayType: EachWayType = WIN): Decimal = status.factoredPrice(price, eachWayType)

   val state: BetState
      get() = status.state

   val selectionId: Any
      get() = irDescriptor.selectionId

   val marketId: Any
      get() = irDescriptor.marketId
}
