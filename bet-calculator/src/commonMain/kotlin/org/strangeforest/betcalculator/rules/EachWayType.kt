package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.util.*

enum class EachWayType(val unitCount: Decimal) {

   WIN(ONE), PLACE(ONE), EACH_WAY(TWO);
}
