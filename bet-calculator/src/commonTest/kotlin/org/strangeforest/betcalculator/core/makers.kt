package org.strangeforest.betcalculator.core

import org.strangeforest.betcalculator.util.*

internal fun makeBetLegs(legCount: Int): List<BetLeg> = IntRange(1, legCount)
   .map { BetLeg((it + 1).dec) }
   .toList()
