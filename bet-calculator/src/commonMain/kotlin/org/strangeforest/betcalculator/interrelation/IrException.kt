package org.strangeforest.betcalculator.interrelation

internal open class IrException(val irType: IrType, message: String) : RuntimeException(message)

internal class IrSelectionsException(irType: IrType, val selectionId1: Any, val selectionId2: Any, message: String) : IrException(irType, message)

internal class MaxWinnersViolationException(irType: IrType, val marketId: Any, val selectionIds: Iterable<Any>, val maxWinners: Int, message: String) : IrException(irType, message)
