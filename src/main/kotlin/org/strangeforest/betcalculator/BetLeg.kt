package org.strangeforest.betcalculator

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.interrelation.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayType.*

data class BetLeg(
   val price: BigDecimal,
   val status: LegStatus = LegStatus.OPEN,
   val irDescriptor: IRDescriptor = IRDescriptor.NO_IR,
   override val banker: Boolean = false
) : BankerAware {

   init {
      require(price >= ONE) { "price must be greater than or equal to 1" }
   }

   constructor(price: String, status: LegStatus = LegStatus.OPEN, irDescriptor: IRDescriptor = IRDescriptor.NO_IR, banker: Boolean = false) :
      this(price.toBigDecimal(), status, irDescriptor, banker)

   fun factoredPrice(eachWayType: EachWayType = WIN): BigDecimal = status.factoredPrice(price, eachWayType)

   val state: BetState
      get() = status.state

   val selectionId: Comparable<*>
      get() = irDescriptor.selectionId

   val marketId: Comparable<*>
      get() = irDescriptor.marketId
}
