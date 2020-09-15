package org.strangeforest.betcalculator.rules

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*

data class Rank(val position: Int, val sharedAmong: Int = 1) {

   init {
      require(position >= 0) { "position cannot be negative" }
      require(sharedAmong >= 0) { "sharedAmong cannot be negative" }
   }

   fun isWon(): Boolean = position == 1

   fun isPlaced(placeTerms: PlaceTerms): Boolean = position in 1..placeTerms.places

   fun deadHeatFactor(placeTerms: PlaceTerms = PlaceTerms.WIN_ONLY): BigDecimal =
      if (position > 0) {
         val placesToShare = placeTerms.places - position + 1
         if (placesToShare < sharedAmong)
            placesToShare.toBigDecimal() / sharedAmong.toBigDecimal()
         else
            ONE
      }
      else
         ZERO

   companion object {
      val NON_RUNNER: Rank = Rank(0, 0)
      val NOT_PLACED: Rank = Rank(0, 1)
   }
}
