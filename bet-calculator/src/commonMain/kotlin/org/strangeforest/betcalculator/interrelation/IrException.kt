package org.strangeforest.betcalculator.interrelation

internal open class IrException(val irType: IrType, message: String) : RuntimeException(message)

internal class IrSelectionsException(irType: IrType, val selectionId1: Comparable<*>, val selectionId2: Comparable<*>, message: String) : IrException(irType, message)

internal class MaxWinnersViolationException(irType: IrType, val marketId: Comparable<*>, val selectionIds: Iterable<Comparable<*>>, val maxWinners: Int, message: String) : IrException(irType, message)
