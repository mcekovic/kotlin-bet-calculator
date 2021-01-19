package org.strangeforest.betcalculator.bettypes

import org.strangeforest.betcalculator.core.*

object BetTypes {

   private val betTypes = HashMap<String, BetType>()

   fun get(betTypeId: String): BetType = requireNotNull(betTypes[betTypeId]) { "Unknown bet type: $betTypeId" }

   private fun register(betType: BetType) {
      betTypes[betType.toString()] = betType
   }

   private fun register(vararg betTypes: BetType) {
      betTypes.forEach(::register)
   }

   init {
      register(Single, Double, Treble, Fourfold, Fivefold, Sixfold, Sevenfold, Eightfold, Accumulator)
      register(Singles, Doubles, Trebles, Fourfolds, Fivefolds, Sixfolds, Sevenfolds, Eightfolds)
      register(Trixie, Yankee, Canadian, Heinz, SuperHeinz, Goliath, FullCover)
      register(Patent, Lucky15, Lucky31, Lucky63, SuperHeinzWithSingles, GoliathWithSingles, FullCoverWithSingles)
      register(Fivespot, Pontoon, Magnificent7)
      register(UnionJack, UnionJackTreble, UnionJackTrixie, UnionJackTriplePlus, UnionJackPatent, UnionJackRoundRobin)
      register(Alphabet, Fido, Sitter, Poly, Lucky7Bingo, Mix, Sundial, TripleYankee, Arkle, PermPatent, PermYankee)
      register(SingleStakesAbout, DoubleStakesAbout, RoundRobin, Flag, SuperFlag, HeinzFlag, SuperHeinzFlag, GoliathFlag)
      register(BookiesNightmare, BlanketYankee, PermRoundRobin, SingleLap, DoubleLap)
      register(Rounder, Roundabout, RoundTheClock, Gyroscope, Banko, Comedy, LiverpoolRoundTheClock, DundeeShuffle)
      for (i in 1..20) {
         register(AccumulatorN(i))
         register(Perms(i))
         register(StrictPerms(i))
         register(FullCoverWithSinglesN(i))
         if (i > 1) {
            register(FullCoverN(i))
            register(SingleStakesAboutN(i))
            register(DoubleStakesAboutN(i))
         }
      }
   }
}
