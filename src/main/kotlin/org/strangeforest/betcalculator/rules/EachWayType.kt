package org.strangeforest.betcalculator.rules

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*

enum class EachWayType(val unitCount: BigDecimal) {

   WIN(ONE), PLACE(ONE), EACH_WAY(TWO);
}
