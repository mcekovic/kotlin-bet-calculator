package org.strangeforest.betcalculator

import assertk.*
import assertk.assertions.*

fun Assert<Decimal>.isEqualByComparingTo(expected: String) = isEqualByComparingTo(expected.dec)
