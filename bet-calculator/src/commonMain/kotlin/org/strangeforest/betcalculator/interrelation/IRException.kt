package org.strangeforest.betcalculator.interrelation

open class IRException(val irType: IRType, message: String) : RuntimeException(message)

class IRSelectionsException(irType: IRType, val selectionId1: Comparable<*>, val selectionId2: Comparable<*>, message: String) : IRException(irType, message)

class MaxWinnersViolationException(irType: IRType, val marketId: Comparable<*>, val selectionIds: Iterable<Comparable<*>>, val maxWinners: Int, message: String) : IRException(irType, message)
