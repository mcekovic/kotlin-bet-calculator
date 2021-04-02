package org.strangeforest.betcalculator.util

import assertk.*
import assertk.assertions.*

internal fun Assert<Decimal>.isEqualByComparingTo(expected: String) = isEqualByComparingTo(expected.dec)
