package org.strangeforest.betcalculator.core

data class BetLegGroup(
   val betType: BetType,
   val legs: List<BetLeg>,
   override val banker: Boolean = false
) : BankerAware {

   init {
      require(legs.isNotEmpty()) { "legs cannot be empty" }
   }

   val legCombinations: List<List<BetLeg>> by lazy {
      betType.bankerAwareCombinations(legs)
         .map(Combination<BetLeg>::items)
         .toList()
   }
}
