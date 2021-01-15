package org.strangeforest.betcalculator

import org.strangeforest.betcalculator.util.*

fun makeBetLegs(legCount: Int): List<BetLeg> = IntRange(1, legCount)
   .map { BetLeg((it + 1).dec) }
   .toList()
