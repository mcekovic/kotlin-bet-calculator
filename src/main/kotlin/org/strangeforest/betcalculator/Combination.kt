package org.strangeforest.betcalculator

class Combination<T>(val items: List<T>, val betType: BetType) {

   fun reversed(): Combination<T> = Combination(items.reversed(), betType)
   fun withBankers(bankerItems: List<T>): Combination<T> = Combination(bankerItems + items, betType)
}
