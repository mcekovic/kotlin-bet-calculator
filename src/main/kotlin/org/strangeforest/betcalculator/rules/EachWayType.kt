package org.strangeforest.betcalculator.rules

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.util.*

enum class EachWayType(val unitCount: BigDecimal) {

   WIN(ONE), PLACE(ONE), EACH_WAY(TWO);
}
