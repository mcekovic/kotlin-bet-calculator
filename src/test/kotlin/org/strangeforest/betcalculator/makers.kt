package org.strangeforest.betcalculator

fun makeBetLegs(legCount: Int): List<BetLeg> = IntRange(1, legCount)
   .map { BetLeg((it + 1).dec) }
   .toList()
