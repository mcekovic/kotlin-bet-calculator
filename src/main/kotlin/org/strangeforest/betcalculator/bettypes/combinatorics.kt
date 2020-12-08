package org.strangeforest.betcalculator.bettypes

import kotlin.streams.*
import org.paukov.combinatorics3.Generator.*

fun <T> combinations(items: List<T>, length: Int): Sequence<List<T>> = combination(items).simple(length).stream().asSequence()

fun <T> cartesianProducts(items: List<List<T>>): Sequence<List<T>> = cartesianProduct(*items.toTypedArray()).stream().asSequence()
