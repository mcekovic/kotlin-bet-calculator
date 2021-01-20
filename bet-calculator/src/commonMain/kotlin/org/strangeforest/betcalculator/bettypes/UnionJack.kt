package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*

internal sealed class BaseUnionJack(private val betType: BetType) : BetType() {

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

internal object UnionJack : BaseUnionJack(Treble)
internal object UnionJackTreble : BaseUnionJack(Treble)
internal object UnionJackTrixie : BaseUnionJack(Trixie)
internal object UnionJackTriplePlus : BaseUnionJack(CompoundBetType(Trixie, Treble))
internal object UnionJackPatent : BaseUnionJack(Patent)
internal object UnionJackRoundRobin : BaseUnionJack(RoundRobin)
