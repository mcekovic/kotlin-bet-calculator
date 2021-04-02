package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.core.*

internal interface LegRules<R> {

   val openStatus: LegStatus
      get() = LegStatus.OPEN

   fun resultedStatus(leg: BetLeg, result: R): LegStatus
}
