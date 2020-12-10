package org.strangeforest.betcalculator.util

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class CartesianProductTest {

	@Test
	fun cartesianProductTest() {
		assertThat(cartesianProduct(listOf(listOf("A", "B"), listOf("C", "D"))).toList()).isEqualTo(listOf(
			listOf("A", "C"),
			listOf("A", "D"),
			listOf("B", "C"),
			listOf("B", "D")
		))
	}

	@Test
	fun emptyItemsTest() {
		assertThat(cartesianProduct(listOf<List<String>>()).toList()).isEmpty()
		assertThat(cartesianProduct(listOf(listOf("A", "B"), listOf())).toList()).isEmpty()
	}
}
