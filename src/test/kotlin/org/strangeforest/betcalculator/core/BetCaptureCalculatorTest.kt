package org.strangeforest.betcalculator.core

import kotlin.test.*
import assertk.*
import org.strangeforest.betcalculator.bettypes.*
import org.strangeforest.betcalculator.bettypes.Double
import org.strangeforest.betcalculator.rules.*
import org.strangeforest.betcalculator.rules.EachWayFormula.*
import org.strangeforest.betcalculator.rules.EachWayType.*

class BetCaptureCalculatorTest {

   // Single

   @Test
   fun calculateSingleBet() {
      val bet = Bet(Single, "10", makeBetLegs(1))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(1).hasStake("10").hasMaxReturn("20")
   }


   // Accumulator

   @Test
   fun calculateDoubleBet() {
      val bet = Bet(Double, "2", makeBetLegs(2))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(1).hasStake("2").hasMaxReturn("12")
   }


   // Perms

   @Test
   fun calculatePermsBet() {
      val bet = Bet(Perms(2), "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("26")
   }


   // Variable Perms

   @Test
   fun calculateVariablePermsBet() {
      val bet = Bet(VariablePerms(2, 3), "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(10).hasStake("10").hasMaxReturn("225")
   }


   // Full Covers

   @Test
   fun calculateTrixieBet() {
      val bet = Bet(Trixie, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(4).hasStake("4").hasMaxReturn("50")
   }

   @Test
   fun calculateGoliathBet() {
      val bet = Bet(Goliath, "1", makeBetLegs(8))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(247).hasStake("247").hasMaxReturn("1814355")
   }

   @Test
   fun calculatePatentBet() {
      val bet = Bet(Patent, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(7).hasStake("7").hasMaxReturn("59")
   }

   @Test
   fun calculateLucky63Bet() {
      val bet = Bet(Lucky63, "1", makeBetLegs(6))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(63).hasStake("63").hasMaxReturn("20159")
   }


   // Union Jack

   @Test
   fun calculateUnionJackBet() {
      val bet = Bet(UnionJack, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(8).hasStake("8").hasMaxReturn("1788")
   }

   @Test
   fun calculateUnionJackTrixieBet() {
      val bet = Bet(UnionJackTrixie, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(32).hasStake("32").hasMaxReturn("2662")
   }

   @Test
   fun calculateUnionJackTriplePlusBet() {
      val bet = Bet(UnionJackTriplePlus, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(40).hasStake("40").hasMaxReturn("4450")
   }

   @Test
   fun calculateUnionJackPatentBet() {
      val bet = Bet(UnionJackPatent, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(56).hasStake("56").hasMaxReturn("2806")
   }

   @Test
   fun calculateUnionJackRoundRobinBet() {
      val bet = Bet(UnionJackRoundRobin, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(80).hasStake("80").hasMaxReturn("3190")
   }


   // Any-to-Come

   @Test
   fun calculateSingleStakesAbout() {
      val bet = Bet(SingleStakesAbout, "1", makeBetLegs(2))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(2).hasStake("2").hasMaxReturn("8")
   }

   @Test
   fun calculateDoubleStakesAbout() {
      val bet = Bet(DoubleStakesAbout, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(6).hasStake("6").hasMaxReturn("42")
   }

   @Test
   fun calculateRoundRobin() {
      val bet = Bet(RoundRobin, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(10).hasStake("10").hasMaxReturn("80")
   }

   @Test
   fun calculateRounder() {
      val bet = Bet(Rounder, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("32")
   }

   @Test
   fun calculateRoundabout() {
      val bet = Bet(Roundabout, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("55")
   }

   @Test
   fun calculateRoundTheClock() {
      val bet = Bet(RoundTheClock, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(4).hasStake("4").hasMaxReturn("164")
   }

   @Test
   fun calculateGyroscope() {
      val bet = Bet(Gyroscope, "1", makeBetLegs(5))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(10).hasStake("10").hasMaxReturn("725")
   }


   // Misc Bet Types

   @Test
   fun calculateFivespotBet() {
      val bet = Bet(Fivespot, "1", makeBetLegs(5))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(15).hasStake("15").hasMaxReturn("1492")
   }

   @Test
   fun calculateAlphabetBet() {
      val bet = Bet(Alphabet, "1", makeBetLegs(6))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(26).hasStake("26").hasMaxReturn("6255")
   }

   @Test
   fun calculateFidoBet() {
      val bet = Bet(Fido, "1", makeBetLegs(5))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(20).hasStake("20").hasMaxReturn("735")
   }

   @Test
   fun calculatePolyBet() {
      val bet = Bet(Poly, "1", makeBetLegs(6))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(10).hasStake("10").hasMaxReturn("641")
   }

   @Test
   fun calculateLucky7BingoBet() {
      val bet = Bet(Lucky7Bingo, "1", makeBetLegs(7))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(13).hasStake("13").hasMaxReturn("42396")
   }

   @Test
   fun calculateMixBet() {
      val bet = Bet(Mix, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(12).hasStake("12").hasMaxReturn("465")
   }

   @Test
   fun calculateSundialBet() {
      val bet = Bet(Sundial, "1", makeBetLegs(7))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(18).hasStake("18").hasMaxReturn("3056")
   }

   @Test
   fun calculateTripleYankeeBet() {
      val bet = Bet(TripleYankee, "1", makeBetLegs(6))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(33).hasStake("33").hasMaxReturn("2655")
   }

   @Test
   fun calculateArkleBet() {
      val bet = Bet(
         Arkle, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("4.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("5.0", LegStatus.open(placeOddsFactor = "0.5"))
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(30).hasStake("30").hasMaxReturn("480.5")
   }

   @Test
   fun calculatePermPatentBet() {
      val bet = Bet(PermPatent, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(28).hasStake("28").hasMaxReturn("338")
   }

   @Test
   fun calculatePermYankeeBet() {
      val bet = Bet(PermYankee, "1", makeBetLegs(5))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(55).hasStake("55").hasMaxReturn("2669")
   }

   @Test
   fun calculateBookiesNightmareBet() {
      val bet = Bet(BookiesNightmare, "1", makeBetLegs(9))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(47).hasStake("47").hasMaxReturn("3638461")
   }

   @Test
   fun calculateBlanketYankeeBet() {
      val bet = Bet(BlanketYankee, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(27).hasStake("27").hasMaxReturn("431")
   }

   @Test
   fun calculatePermRoundRobinBet() {
      val bet = Bet(PermRoundRobin, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(40).hasStake("40").hasMaxReturn("440")
   }

   @Test
   fun calculateSingleLapBet() {
      val bet = Bet(SingleLap, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("15")
   }

   @Test
   fun calculateDoubleLapBet() {
      val bet = Bet(DoubleLap, "1", makeBetLegs(4))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(4).hasStake("4").hasMaxReturn("34")
   }

   @Test
   fun calculateBankoBet() {
      val bet = Bet(Banko, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(7).hasStake("7").hasMaxReturn("105")
   }

   @Test
   fun calculateComedyBet() {
      val bet = Bet(Comedy, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(13).hasStake("13").hasMaxReturn("135")
   }

   @Test
   fun calculateLiverpoolRoundTheClockBet() {
      val bet = Bet(LiverpoolRoundTheClock, "1", makeBetLegs(3))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(13).hasStake("13").hasMaxReturn("147")
   }

   @Test
   fun calculateDundeeShuffleBet() {
      val bet = Bet(
         DundeeShuffle, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("4.0", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("5.0", LegStatus.open(placeOddsFactor = "0.5"))
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(10).hasStake("10").hasMaxReturn("297.75")
   }


   // Each-Way

   @Test
   fun calculateEachWaySingleBet() {
      val bet = Bet(Single, "10", listOf(BetLeg("2", LegStatus.open("0.5"))), rules = BetRules(EACH_WAY))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(2).hasStake("20").hasMaxReturn("35")
   }

   @Test
   fun calculateEachWayTrebleBet() {
      val bet = Bet(
         Treble, "10", listOf(
            BetLeg("2", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("3", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("4")
         ), rules = BetRules(EACH_WAY)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(2).hasStake("20").hasMaxReturn("360")
   }

   @Test
   fun calculateEachWayPatentBetWithEquallyDividedFormula() {
      val bet = Bet(
         Patent, "10", listOf(
            BetLeg("2", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("4")
         ), rules = BetRules(EACH_WAY, EQUALLY_DIVIDED)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(14).hasStake("140").hasMaxReturn("833.125")
   }

   @Test
   fun winPrecedenceEachWayFormulaIsOnlyForAnyToComeBets() {
      assertFailsWith<IllegalArgumentException> {
         Bet(
            Double, "10", listOf(
               BetLeg("2", LegStatus.open(placeOddsFactor = "0.25")),
               BetLeg("3", LegStatus.open(placeOddsFactor = "0.25"))
            ), rules = BetRules(EACH_WAY, WIN_PRECEDENCE)
         )
      }
   }


   // Place

   @Test
   fun calculatePlaceSingleBet() {
      val bet = Bet(Single, "10", listOf(BetLeg("2", LegStatus.open(placeOddsFactor = "0.5"))), rules = BetRules(PLACE))

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(1).hasStake("10").hasMaxReturn("15")
   }

   @Test
   fun calculatePlaceTrebleBet() {
      val bet = Bet(
         Treble, "10", listOf(
            BetLeg("2", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("3", LegStatus.open(placeOddsFactor = "0.5")),
            BetLeg("4")
         ), rules = BetRules(PLACE)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(1).hasStake("10").hasMaxReturn("120")
   }


   // Any-to-Come Each-Way

   @Test
   fun calculateEachWaySingleStakesAboutBet() {
      val bet = Bet(
         SingleStakesAbout, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("4.0", LegStatus.open(placeOddsFactor = "0.25"))
         ), rules = BetRules(EACH_WAY)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(12).hasStake("12").hasMaxReturn("42")
   }

   @Test
   fun calculateEachWaySingleStakesAboutBetWithWinPrecedenceFormula() {
      val bet = Bet(
         SingleStakesAbout, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("4.0", LegStatus.open(placeOddsFactor = "0.25"))
         ), rules = BetRules(EACH_WAY, eachWayAnyToComeFormula = WIN_PRECEDENCE)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(12).hasStake("12").hasMaxReturn("42")
   }

   @Test
   fun calculateEachWaySingleStakesAboutBetWithWinPlaceFormula() {
      val bet = Bet(
         SingleStakesAbout, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("4.0", LegStatus.open(placeOddsFactor = "0.25"))
         ), rules = BetRules(EACH_WAY, eachWayAnyToComeFormula = WIN_PLACE)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(12).hasStake("12").hasMaxReturn("42")
   }

   @Test
   fun calculateEachWayDoubleStakesAboutBet() {
      val bet = Bet(
         DoubleStakesAbout, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.25"))
         ), rules = BetRules(EACH_WAY)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(4).hasStake("4").hasMaxReturn("14")
   }

   @Test
   fun calculateEachWayDoubleStakesAboutWithWinPrecedenceFormula() {
      val bet = Bet(
         DoubleStakesAbout, "1", listOf(
            BetLeg("2.0", LegStatus.open(placeOddsFactor = "0.25")),
            BetLeg("3.0", LegStatus.open(placeOddsFactor = "0.25"))
         ), rules = BetRules(EACH_WAY, eachWayAnyToComeFormula = WIN_PRECEDENCE)
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(4).hasStake("4").hasMaxReturn("14.75")
   }


   // Perms

   @Test
   fun calculatePermsWithBankersBet() {
      val bet = Bet(
         Perms(2), "1", listOf(
            BetLeg("2", banker = true),
            BetLeg("3"),
            BetLeg("4"),
            BetLeg("5")
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("94")
   }


   // Groups

   @Test
   fun calculatePermsBetWithAccumulatorGroups() {
      val bet = Bet(
         Perms(2), "1", groups = listOf(
            BetLegGroup(
               Accumulator, listOf(
                  BetLeg("2"),
                  BetLeg("3")
               )
            ),
            BetLegGroup(
               Accumulator, listOf(
                  BetLeg("4")
               )
            ),
            BetLegGroup(
               Accumulator, listOf(
                  BetLeg("5")
               )
            )
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(3).hasStake("3").hasMaxReturn("74")
   }

   @Test
   fun calculateAccumulatorBetWithPermsGroups() {
      val bet = Bet(
         Accumulator, "1", groups = listOf(
            BetLegGroup(
               Perms(2), listOf(
                  BetLeg("2"),
                  BetLeg("3"),
                  BetLeg("4")
               )
            ),
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("5"),
                  BetLeg("6")
               )
            )
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(6).hasStake("6").hasMaxReturn("286")
   }

   @Test
   fun calculatePermsBetWithPermsGroups() {
      val bet = Bet(
         Perms(2), "1", groups = listOf(
            BetLegGroup(
               Perms(2), listOf(
                  BetLeg("2"),
                  BetLeg("3"),
                  BetLeg("4")
               )
            ),
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("5"),
                  BetLeg("6")
               )
            ),
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("7"),
                  BetLeg("8")
               )
            )
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(16).hasStake("16").hasMaxReturn("841")
   }

   @Test
   fun calculatePermsBetWithPermsBankerGroupsWithBankerLegs() {
      val bet = Bet(
         Perms(2), "1", groups = listOf(
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("2"),
                  BetLeg("3"),
               ), banker = true
            ),
            BetLegGroup(
               Perms(2), listOf(
                  BetLeg("4", banker = true),
                  BetLeg("5"),
                  BetLeg("6"),
                  BetLeg("7")
               )
            ),
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("8", banker = true),
                  BetLeg("9"),
                  BetLeg("10")
               )
            ),
            BetLegGroup(
               Perms(1), listOf(
                  BetLeg("11", banker = true),
                  BetLeg("12"),
                  BetLeg("13")
               )
            )
         )
      )

      val result = BetCaptureCalculator.calculate(bet)

      assertThat(result).hasUnitCount(32).hasStake("32").hasMaxReturn("1122780")
   }
}
