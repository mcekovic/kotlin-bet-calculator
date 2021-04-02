package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.rules.EachWayFormula.*
import org.strangeforest.betcalculator.rules.EachWayType.*

internal data class BetRules(
	val eachWayType: EachWayType = WIN,
	val eachWayFormula: EachWayFormula = WIN_PLACE,
	val eachWayAnyToComeFormula: EachWayFormula = EQUALLY_DIVIDED
) {

	init {
	   require(eachWayFormula != WIN_PRECEDENCE) { "eachWayFormula cannot be WIN_PRECEDENCE"}
	}

	fun eachWay(): BetRules = BetRules(EACH_WAY, eachWayFormula, eachWayAnyToComeFormula)

	companion object {
		val DEFAULT = BetRules()
	}
}
