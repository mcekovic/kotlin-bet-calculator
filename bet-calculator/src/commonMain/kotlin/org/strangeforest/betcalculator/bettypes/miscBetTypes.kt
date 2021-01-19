package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*
import org.strangeforest.betcalculator.util.*

object Alphabet : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 6)
      return sequenceOf(
         Patent.combinations(items.subList(0, 3)),
         Patent.combinations(items.subList(3, 6)),
         Yankee.combinations(items.subList(1, 5)),
         Sixfold.combinations(items)
      ).flatten()
   }
}

object Fido : BaseCompoundBetType(StrictPermsN(2, 5), StrictPermsN(3, 5))
object Sitter : BaseCompoundBetType(StrictPerms(2), StrictPerms(3))

object Poly : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 6)
      return sequenceOf(
         Double.combinations(items.subList(0, 2)),
         Double.combinations(items.subList(1, 3)),
         Double.combinations(items.subList(2, 4)),
         Double.combinations(items.subList(3, 5)),
         Double.combinations(items.subList(4, 6)),
         Treble.combinations(items.slice(listOf(0, 2, 4))),
         Treble.combinations(items.slice(listOf(0, 2, 5))),
         Treble.combinations(items.slice(listOf(0, 3, 5))),
         Treble.combinations(items.slice(listOf(1, 3, 5))),
         Fourfold.combinations(items.slice(listOf(0, 1, 4, 5)))
      ).flatten()
   }
}

object Lucky7Bingo : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 7)
      return sequenceOf(
         Double.combinations(items.slice(listOf(0, 1))),
         Double.combinations(items.slice(listOf(1, 6))),
         Double.combinations(items.slice(listOf(5, 6))),
         Double.combinations(items.slice(listOf(0, 5))),
         Double.combinations(items.slice(listOf(0, 6))),
         Double.combinations(items.slice(listOf(1, 5))),
         Double.combinations(items.slice(listOf(2, 3))),
         Double.combinations(items.slice(listOf(2, 4))),
         Double.combinations(items.slice(listOf(3, 4))),
         Treble.combinations(items.slice(listOf(0, 3, 6))),
         Treble.combinations(items.slice(listOf(1, 3, 5))),
         Fivefold.combinations(items.slice(listOf(0, 1, 3, 5, 6))),
         Sevenfold.combinations(items)
      ).flatten()
   }
}

object Mix : BaseCompoundBetType(Yankee, AccumulatorN(4))

object Sundial : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 7)
      return sequenceOf(
         Patent.combinations(items.subList(0, 3)),
         Yankee.combinations(items.subList(3, 7))
      ).flatten()
   }
}

object TripleYankee : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 6)
      return sequenceOf(
         Yankee.combinations(items.subList(0, 4)),
         Yankee.combinations(items.subList(2, 6)),
         Yankee.combinations(items.slice(listOf(0, 1, 4, 5)))
      ).flatten()
   }
}

object Arkle : BaseCompoundBetType(PermPatent, EachWayAccumulatorN(4))

object BookiesNightmare : BetType() {

   override fun <T> combinations(items: List<T>): Sequence<Combination<T>> {
      requireItemsSize(items, 9)
      return sequenceOf(
         Patent.combinations(items.subList(0, 3)),
         RoundRobin.combinations(items.subList(3, 6)),
         Patent.combinations(items.subList(6, 9)),
         Yankee.combinations(items.subList(0, 4)),
         Yankee.combinations(items.subList(5, 9)),
         AccumulatorN(9).combinations(items)
      ).flatten()
   }
}

object BlanketYankee : BaseCompoundBetType(Flag, StrictPermsN(1, 4))

object Banko : BaseCompoundBetType(Trixie, Roundabout)
object Comedy : BaseCompoundBetType(RoundRobin, Roundabout)
object LiverpoolRoundTheClock : BaseCompoundBetType(Trixie, Roundabout, DoubleStakesAboutN(3))

object DundeeShuffle : BaseCompoundBetType(
   ReducedStakePermsAnyToComeSubTypeOfRestN(HALF, 1, 4, "0.25".dec, Trixie),
   ReducedStakePermsAnyToComeRestN(TWO, 2, 4, HALF),
   ReducedStakeStrictPermsN(3, 4, HALF),
   EachWayAccumulatorN(4, HALF)
)
