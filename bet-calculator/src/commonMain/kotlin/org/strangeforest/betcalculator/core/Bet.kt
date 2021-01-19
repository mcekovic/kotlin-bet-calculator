package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.util.*

data class Bet(
   val betType: BetType,
   val unitStake: Decimal,
   val legs: List<BetLeg> = emptyList(),
   val groups: List<BetLegGroup> = emptyList(),
   val rules: BetRules = BetRules.DEFAULT
) {

   init {
      require(unitStake > ZERO) { "unitStake must be positive" }
      require(legs.isNotEmpty() != groups.isNotEmpty()) { "either legs or groups must be specified" }
   }

   constructor(betType: BetType, unitStake: String, legs: List<BetLeg> = emptyList(), groups: List<BetLegGroup> = emptyList(), rules: BetRules = BetRules.DEFAULT) :
      this(betType, unitStake.dec, legs, groups, rules)

   val units: Sequence<BetUnit>
      get() {
         return if (legs.isNotEmpty())
            betType.bankerAwareCombinations(legs).map(this::toUnit)
         else {
            betType.bankerAwareCombinations(groups)
               .map { groupCombination -> cartesianProduct(
                  groupCombination.items.map(BetLegGroup::legCombinations)
               )}.flatten()
               .map { legs -> toUnit(betType.toCombination(legs.flatten()))}
         }
      }

   private fun toUnit(combination: Combination<BetLeg>): BetUnit {
      return combination.betType.createUnit(unitStake, combination.items, rules)
   }
}
