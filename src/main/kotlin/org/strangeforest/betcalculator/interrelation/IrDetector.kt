package org.strangeforest.betcalculator.interrelation

import kotlin.collections.set
import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.interrelation.IrType.*

internal class IrDetector {

   private val irCache: MutableMap<IrKey, IrResult> = HashMap()

   fun checkInterrelationOrSkip(unit: BetUnit): Boolean {
      val result = areInterrelated(unit.legs)
      return result.isInterrelated() && (unit.betType.canSkipUnits || throw result.toIrException())
   }

   private fun areInterrelated(legs: List<BetLeg>): IrResult {
      val count = legs.size
      for (i in 0 until count - 1) {
         val desc1 = legs[i].irDescriptor
         for (j in i + 1 until count) {
            val desc2 = legs[j].irDescriptor
            val result = cachedAreInterrelated(desc1, desc2)
            if (result.isInterrelated()) return result
         }
      }
      return isMaxWinnersViolated(legs)
   }

   private fun cachedAreInterrelated(desc1: IrDescriptor, desc2: IrDescriptor) =
      irCache.getOrPut(IrKey(desc1.selectionId, desc2.selectionId)) { areInterrelated(desc1, desc2) }

   companion object {

      private data class IrKey(val selectionId1: Any, val selectionId2: Any)

      fun getInterrelations(legs: List<BetLeg>): List<IrResult> {
         val results = ArrayList<IrResult>()
         val count = legs.size
         for (i in 0 until count - 1) {
            val desc1 = legs[i].irDescriptor
            for (j in i + 1 until count) {
               val desc2 = legs[j].irDescriptor
               val result = areInterrelated(desc1, desc2)
               if (result.isInterrelated()) results += result
            }
         }
         val result = isMaxWinnersViolated(legs)
         if (result.isInterrelated()) results += result
         return results
      }

      fun areInterrelated(desc1: IrDescriptor, desc2: IrDescriptor): IrResult {
         if (desc1.selectionId == desc2.selectionId)
            return IrSelectionsResult(SAME_SELECTION, desc1.selectionId, desc2.selectionId, "Same selection (${desc1.selectionId})")
         if (desc1.marketId == desc2.marketId) {
            if (desc1.maxWinners != desc2.maxWinners)
               throw IllegalStateException("Different maxWinners for different Legs on the same market")
            if (desc1.maxWinners == 1)
               return IrSelectionsResult(SAME_MARKET, desc1.selectionId, desc2.selectionId, "Same market (${desc1.marketId})")
            return NotInterrelated
         }
         if (desc1.eventId == desc2.eventId && desc1.tag outcomeMatchedWith desc2.tag)
            return IrSelectionsResult(SAME_EVENT, desc1.selectionId, desc2.selectionId, "Same event (${desc1.eventId}) and matching outcome types")
         if (desc1.tag groupInterrelatedWith desc2.tag)
            return IrSelectionsResult(GROUP, desc1.selectionId, desc2.selectionId, "Same group and matching outcome types")
         if (desc1.tag ccInterrelatedWith desc2.tag)
            return IrSelectionsResult(CAUSE_CONSEQUENCE, desc1.selectionId, desc2.selectionId, "Cause -> Consequence and matching outcome and dependent outcome types")
         if (desc2.tag ccInterrelatedWith desc1.tag)
            return IrSelectionsResult(CAUSE_CONSEQUENCE, desc1.selectionId, desc2.selectionId, "Consequence <- Cause and matching dependent outcome and outcome types")
         return NotInterrelated
      }

      private fun isMaxWinnersViolated(legs: List<BetLeg>): IrResult {
         var marketCounts: MutableMap<Any, Int>? = null
         for (leg in legs) {
            val desc = leg.irDescriptor
            val marketId = desc.marketId
            val maxWinners = desc.maxWinners
            if (maxWinners != null && maxWinners > 1) {
               if (marketCounts == null)
                  marketCounts = HashMap()
               var marketCount = marketCounts[marketId]
               val newCount = if (marketCount != null) ++marketCount else 1
               if (newCount > maxWinners)
                  return MaxWinnersViolationIrResult(marketId, findSelectionIdsForMarket(legs, marketId), maxWinners)
               else
                  marketCounts[marketId] = newCount
            }
         }
         return NotInterrelated
      }

      private fun findSelectionIdsForMarket(legs: List<BetLeg>, marketId: Any): List<Any> =
         legs.asSequence()
            .filter { leg -> leg.marketId == marketId }
            .map(BetLeg::selectionId)
            .toList()
   }
}