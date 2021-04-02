package org.strangeforest.betcalculator

import kotlin.test.*
import kotlin.time.*
import assertk.*
import org.strangeforest.betcalculator.bettypes.*

class BetCalculationPT {

   @Test @Ignore
   @ExperimentalTime
   fun bigPermsTest() {
      var units = 0.0
      val duration = measureTime {
         val bet = Bet(FullCoverWithSinglesN(22), "1", makeBetLegs(22))

         val result = BetCaptureCalculator.calculate(bet)

         assertThat(result).isResult(4194303, "4194303", "310224200866619719679999")

         units += result.unitCount.toDouble()
      }
      println("Units/sec: " + units / duration.inSeconds)
   }

   @Test @Ignore
   @ExperimentalTime
   fun smallPermsTest() {
      var units = 0.0
      val duration = measureTime {
         repeat(10000) {
            val bet = Bet(Perms(3), "1", makeBetLegs(20))

            val result = BetCaptureCalculator.calculate(bet)

            assertThat(result).isResult(1140, "1140", "1664970")

            units += result.unitCount.toDouble()
         }
      }
      println("Units/sec: " + units / duration.inSeconds)
   }
}