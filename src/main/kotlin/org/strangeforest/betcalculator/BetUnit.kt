package org.strangeforest.betcalculator

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.BetState.*
import org.strangeforest.betcalculator.bettypes.*
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayFormula.*
import org.strangeforest.betcalculator.rules.EachWayType.*
import org.strangeforest.betcalculator.util.*

open class BetUnit(
   val unitStake: BigDecimal,
   val legs: List<BetLeg>,
   val betType: BetType = Accumulator,
   val rules: BetRules = BetRules.DEFAULT,
   val unitCountFactor: BigDecimal = ONE
) {

   constructor(unitStake: String, legs: List<BetLeg>, betType: BetType = Accumulator, rules: BetRules = BetRules.DEFAULT, unitCountFactor: String = ONE.toString()) :
      this(unitStake.toBigDecimal(), legs, betType, rules, unitCountFactor.toBigDecimal())

   val unitCount: BigDecimal by lazy {
      unitCountFactor * rules.eachWayType.unitCount
   }

   val stake: BigDecimal by lazy {
      unitCount * unitStake
   }

   val currentReturn: BigDecimal
      get() = if ((WON..VOID).contains(state)) calculatedReturn else ZERO

   val maxReturn: BigDecimal
      get() = if (state != LOST) calculatedReturn else ZERO

   private val calculatedReturn: BigDecimal by lazy {
      stake * cumulativePrice
   }

   open val cumulativePrice: BigDecimal by lazy {
      when (val eachWayType = rules.eachWayType) {
         WIN, PLACE ->
            legs.asSequence()
               .map { leg -> leg.factoredPrice(eachWayType) }
               .reduce(BigDecimal::times)
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
