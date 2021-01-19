package org.strangeforest.betcalculator

import kotlin.js.*
import org.strangeforest.betcalculator.util.dec

typealias KBetCaptureCalculator = org.strangeforest.betcalculator.core.BetCaptureCalculator
typealias KBetSettlementCalculator = org.strangeforest.betcalculator.core.BetSettlementCalculator
typealias KBet = org.strangeforest.betcalculator.core.Bet
typealias KBetTypes = org.strangeforest.betcalculator.bettypes.BetTypes
typealias KBetLeg = org.strangeforest.betcalculator.core.BetLeg
typealias KBetLegGroup = org.strangeforest.betcalculator.core.BetLegGroup
typealias KBetRules = org.strangeforest.betcalculator.rules.BetRules
typealias KEachWayType = org.strangeforest.betcalculator.rules.EachWayType
typealias KEachWayFormula = org.strangeforest.betcalculator.rules.EachWayFormula
typealias KLegStatus = org.strangeforest.betcalculator.core.LegStatus
typealias KIRDescriptor = org.strangeforest.betcalculator.interrelation.IrDescriptor
typealias KIRTag = org.strangeforest.betcalculator.interrelation.IrTag
typealias KBetCaptureResult = org.strangeforest.betcalculator.core.BetCaptureResult
typealias KBetSettlementResult = org.strangeforest.betcalculator.core.BetSettlementResult

class Bet(
   val betType: String,
   val unitStake: String,
   val legs: Array<BetLeg>
) {
   var groups: Array<BetLegGroup> = emptyArray()
   var rules: BetRules = BetRules()
}

class BetLeg(
   val price: String
) {
   var status: LegStatus? = null
   var irDescriptor: IrDescriptor? = null
   var banker: Boolean = false
}

class BetLegGroup(
   val betType: String,
   val legs: Array<BetLeg>
) {
   var banker: Boolean = false
}

class BetRules() {
   var eachWayType: String = KEachWayType.WIN.toString()
   var eachWayFormula: String = KEachWayFormula.WIN_PLACE.toString()
   var eachWayAnyToComeFormula: String = KEachWayFormula.EQUALLY_DIVIDED.toString()
}

class LegStatus(
   val winFactor: String,
   val voidFactor: String
) {
   var placeOddsFactor: String = "1"
   var resulted: Boolean = false
}

class IrDescriptor(
   val selectionId: Comparable<*>,
   val marketId: Comparable<*>,
   val eventId: Comparable<*>
) {
   var maxWinners: Int = 1
   var tag: String = ""
}

data class BetCaptureResult(
   val unitCount: String,
   val stake: String,
   val maxReturn: String,
   val state: String
)

data class BetSettlementResult(
   val currentReturn: String,
   val maxReturn: String,
   val state: String
)

@JsName("calculateCapture")
fun calculateCapture(bet: Bet): BetCaptureResult {
   return fromBetCaptureResult(KBetCaptureCalculator.calculate(toBet(bet)))
}

@JsName("calculateSettlement")
fun calculateSettlement(bet: Bet): BetSettlementResult {
   return fromBetSettlementResult(KBetSettlementCalculator.calculate(toBet(bet)))
}

private fun toBet(bet: Bet) = KBet(
   KBetTypes.get(bet.betType),
   bet.unitStake.dec,
   bet.legs.asSequence().map(::toBetLeg).toList(),
   bet.groups.asSequence().map(::toBetLegGroup).toList(),
   toBetRules(bet.rules)
)

private fun toBetLeg(leg: BetLeg): KBetLeg = KBetLeg(
   leg.price,
   toLegStatus(leg.status),
   toIrDescriptor(leg.irDescriptor),
   leg.banker
)

private fun toBetLegGroup(group: BetLegGroup) = KBetLegGroup(
   KBetTypes.get(group.betType),
   group.legs.asSequence().map(::toBetLeg).toList(),
   group.banker
)

private fun toBetRules(rules: BetRules) = KBetRules(
   KEachWayType.valueOf(rules.eachWayType),
   KEachWayFormula.valueOf(rules.eachWayFormula),
   KEachWayFormula.valueOf(rules.eachWayAnyToComeFormula)
)

private fun toLegStatus(status: LegStatus?) = if (status == null) KLegStatus.OPEN else KLegStatus(
   status.winFactor.dec,
   status.voidFactor.dec,
   status.placeOddsFactor.dec,
   status.resulted
)

private fun toIrDescriptor(descriptor: IrDescriptor?) = if (descriptor == null) KIRDescriptor.NO_IR else KIRDescriptor(
   descriptor.selectionId,
   descriptor.marketId,
   descriptor.eventId,
   descriptor.maxWinners,
   KIRTag.of(descriptor.tag)
)

private fun fromBetCaptureResult(result: KBetCaptureResult) = BetCaptureResult(
   result.unitCount.toString(),
   result.stake.toString(),
   result.maxReturn.toString(),
   result.state.toString()
)

private fun fromBetSettlementResult(result: KBetSettlementResult) = BetSettlementResult(
   result.currentReturn.toString(),
   result.maxReturn.toString(),
   result.state.toString()
)