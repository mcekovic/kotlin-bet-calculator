package org.strangeforest.betcalculator.rules

import java.math.*
import java.math.BigDecimal.*
import org.strangeforest.betcalculator.*

data class EachWayAmounts(val win: BigDecimal, val place: BigDecimal) {

	constructor(win: String, place: String) : this(win.toBigDecimal(), place.toBigDecimal())

	operator fun plus(other: EachWayAmounts) = EachWayAmounts(win + other.win, place + other.place)

	operator fun times(other: EachWayAmounts) = EachWayAmounts(win * other.win, place * other.place)

	val total: BigDecimal
		get() = win + place

	val equallyDivided: EachWayAmounts
		get() {
			val half = total / TWO
			return EachWayAmounts(half, half)
		}

	fun winPrecedence(maxWin: BigDecimal): EachWayAmounts {
		val total = total
		return if (total < maxWin) EachWayAmounts(total, ZERO) else EachWayAmounts(maxWin, total - maxWin)
	}

	companion object {
		val EW_ZERO = EachWayAmounts(ZERO, ZERO)
	}
}
