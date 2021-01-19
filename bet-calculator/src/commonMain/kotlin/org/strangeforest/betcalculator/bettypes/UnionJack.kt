package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*

sealed class BaseUnionJack(private val betType: BetType) : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 9)
      return sequenceOf(
         betType.combinations(items.slice(listOf(0, 1, 2))),
         betType.combinations(items.slice(listOf(3, 4, 5))),
         betType.combinations(items.slice(listOf(6, 7, 8))),

         betType.combinations(items.slice(listOf(0, 3, 6))),
         betType.combinations(items.slice(listOf(1, 4, 7))),
         betType.combinations(items.slice(listOf(2, 5, 8))),

         betType.combinations(items.slice(listOf(0, 4, 8))),
         betType.combinations(items.slice(listOf(2, 4, 6)))
      ).flatten()
   }
}

object UnionJack : BaseUnionJack(Treble)
object UnionJackTreble : BaseUnionJack(Treble)
object UnionJackTrixie : BaseUnionJack(Trixie)
object UnionJackTriplePlus : BaseUnionJack(CompoundBetType(Trixie, Treble))
object UnionJackPatent : BaseUnionJack(Patent)
object UnionJackRoundRobin : BaseUnionJack(RoundRobin)
