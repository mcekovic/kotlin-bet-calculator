package org.strangeforest.betcalculator.rules

import org.strangeforest.betcalculator.*

data class EachWayAmounts(val win: Decimal, val place: Decimal) {

	constructor(win: String, place: String) : this(win.dec, place.dec)

	operator fun plus(other: EachWayAmounts) = EachWayAmounts(win + other.win, place + other.place)

	operator fun times(other: EachWayAmounts) = EachWayAmounts(win * other.win, place * other.place)

	val total: Decimal
		get() = win + place

	val equallyDivided: EachWayAmounts
		get() {
			val half = total / TWO
			return EachWayAmounts(half, half)
		}

	fun winPrecedence(maxWin: Decimal): EachWayAmounts {
		val total = total
		return if (total < maxWin) EachWayAmounts(total, ZERO) else EachWayAmounts(maxWin, total - maxWin)
	}

	companion object {
		val EW_ZERO = EachWayAmounts(ZERO, ZERO)
	}
}
