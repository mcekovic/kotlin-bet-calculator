package org.strangeforest.betcalculator.rules

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*

data class PlaceTerms(val places: Int, val reduction: BigDecimal) {

   init {
      require(places > 0) { "places must be positive" }
      require(reduction >= ONE) { "reduction must be greater then or equal to 1" }
   }

   constructor(places: Int, reduction: String) : this(places, reduction.toBigDecimal())

   val reductionFactor: BigDecimal
      get() = ONE / reduction

   val winOnly: Boolean
      get() = places == 1 && reduction == ONE

   val placeOnly: Boolean
      get() = places > 1 && reduction == ONE

   companion object {
      val WIN_ONLY: PlaceTerms = PlaceTerms(1, ONE)
   }
}
