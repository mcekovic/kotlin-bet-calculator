package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.core.BetState.*
import org.strangeforest.betcalculator.bettypes.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayFormula.*
import org.strangeforest.betcalculator.rules.EachWayType.*
import org.strangeforest.betcalculator.util.*

internal open class BetUnit(
   val unitStake: Decimal,
   val legs: List<BetLeg>,
   val betType: BetType = Accumulator,
   val rules: BetRules = BetRules.DEFAULT,
   val unitCountFactor: Decimal = ONE
) {

   constructor(unitStake: String, legs: List<BetLeg>, betType: BetType = Accumulator, rules: BetRules = BetRules.DEFAULT, unitCountFactor: String = ONE.toString()) :
      this(unitStake.dec, legs, betType, rules, unitCountFactor.dec)

   val unitCount: Decimal by lazy {
      unitCountFactor * rules.eachWayType.unitCount
   }

   val stake: Decimal by lazy {
      unitCount * unitStake
   }

   val currentReturn: Decimal
      get() = if ((WON..VOID).contains(state)) calculatedReturn else ZERO

   val maxReturn: Decimal
      get() = if (state != LOST) calculatedReturn else ZERO

   private val calculatedReturn: Decimal by lazy {
      stake * cumulativePrice
   }

   open val cumulativePrice: Decimal by lazy {
      when (val eachWayType = rules.eachWayType) {
         WIN, PLACE ->
            legs.asSequence()
               .map { leg -> leg.factoredPrice(eachWayType) }
               .reduce(Decimal::times)
         EACH_WAY ->
            legs.asSequence()
               .map { leg -> distributeEachWay(EachWayAmounts(leg.factoredPrice(WIN), leg.factoredPrice(PLACE))) }
               .reduce(EachWayAmounts::times)
               .total / EACH_WAY.unitCount
      }
   }

   private fun distributeEachWay(amounts: EachWayAmounts) = when (rules.eachWayFormula) {
      WIN_PLACE -> amounts
      EQUALLY_DIVIDED -> amounts.equallyDivided
      WIN_PRECEDENCE -> throw IllegalStateException()
   }

   val state: BetState by lazy {
      legs.asSequence()
         .map(BetLeg::state)
         .reduce(BetState::times)
   }
}
