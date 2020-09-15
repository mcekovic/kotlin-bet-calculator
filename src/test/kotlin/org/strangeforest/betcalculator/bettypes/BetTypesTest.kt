package org.strangeforest.betcalculator.bettypes

import kotlin.test.*
import assertk.*
import assertk.assertions.*

class BetTypesTest {

	@Test
	fun accumulators() {
		assertThat(BetTypes.get("Accumulator")).hasToString("Accumulator")
		assertThat(BetTypes.get("Single")).hasToString("Single")
		assertThat(BetTypes.get("Double")).hasToString("Double")
		assertThat(BetTypes.get("Treble")).hasToString("Treble")
		assertThat(BetTypes.get("Fourfold")).hasToString("Fourfold")
		assertThat(BetTypes.get("Fivefold")).hasToString("Fivefold")
		assertThat(BetTypes.get("Sixfold")).hasToString("Sixfold")
		assertThat(BetTypes.get("Sevenfold")).hasToString("Sevenfold")
		assertThat(BetTypes.get("Eightfold")).hasToString("Eightfold")
		assertThat(BetTypes.get("Accumulator1")).hasToString("Accumulator1")
		assertThat(BetTypes.get("Accumulator20")).hasToString("Accumulator20")
	}

	@Test
	fun perms() {
		assertThat(BetTypes.get("Singles")).hasToString("Singles")
		assertThat(BetTypes.get("Doubles")).hasToString("Doubles")
		assertThat(BetTypes.get("Trebles")).hasToString("Trebles")
		assertThat(BetTypes.get("Fourfolds")).hasToString("Fourfolds")
		assertThat(BetTypes.get("Fivefolds")).hasToString("Fivefolds")
		assertThat(BetTypes.get("Sixfolds")).hasToString("Sixfolds")
		assertThat(BetTypes.get("Sevenfolds")).hasToString("Sevenfolds")
		assertThat(BetTypes.get("Eightfolds")).hasToString("Eightfolds")
		assertThat(BetTypes.get("Perms1")).hasToString("Perms1")
		assertThat(BetTypes.get("Perms20")).hasToString("Perms20")
		assertThat(BetTypes.get("StrictPerms1")).hasToString("StrictPerms1")
		assertThat(BetTypes.get("StrictPerms20")).hasToString("StrictPerms20")
	}

	@Test
	fun fullCovers() {
		assertThat(BetTypes.get("FullCover")).hasToString("FullCover")
		assertThat(BetTypes.get("Trixie")).hasToString("Trixie")
		assertThat(BetTypes.get("Yankee")).hasToString("Yankee")
		assertThat(BetTypes.get("Canadian")).hasToString("Canadian")
		assertThat(BetTypes.get("Heinz")).hasToString("Heinz")
		assertThat(BetTypes.get("SuperHeinz")).hasToString("SuperHeinz")
		assertThat(BetTypes.get("Goliath")).hasToString("Goliath")
		assertThat(BetTypes.get("FullCover2")).hasToString("FullCover2")
		assertThat(BetTypes.get("FullCover20")).hasToString("FullCover20")
	}

	@Test
	fun fullCoversWithSingles() {
		assertThat(BetTypes.get("FullCoverWithSingles")).hasToString("FullCoverWithSingles")
		assertThat(BetTypes.get("Patent")).hasToString("Patent")
		assertThat(BetTypes.get("Lucky15")).hasToString("Lucky15")
		assertThat(BetTypes.get("Lucky31")).hasToString("Lucky31")
		assertThat(BetTypes.get("Lucky63")).hasToString("Lucky63")
		assertThat(BetTypes.get("SuperHeinzWithSingles")).hasToString("SuperHeinzWithSingles")
		assertThat(BetTypes.get("GoliathWithSingles")).hasToString("GoliathWithSingles")
		assertThat(BetTypes.get("FullCoverWithSingles1")).hasToString("FullCoverWithSingles1")
		assertThat(BetTypes.get("FullCoverWithSingles20")).hasToString("FullCoverWithSingles20")
	}

	@Test
	fun straightFullCovers() {
		assertThat(BetTypes.get("Fivespot")).hasToString("Fivespot")
		assertThat(BetTypes.get("Pontoon")).hasToString("Pontoon")
		assertThat(BetTypes.get("Magnificent7")).hasToString("Magnificent7")
	}

	@Test
	fun unionJacks() {
		assertThat(BetTypes.get("UnionJack")).hasToString("UnionJack")
		assertThat(BetTypes.get("UnionJackTreble")).hasToString("UnionJackTreble")
		assertThat(BetTypes.get("UnionJackTrixie")).hasToString("UnionJackTrixie")
		assertThat(BetTypes.get("UnionJackTriplePlus")).hasToString("UnionJackTriplePlus")
		assertThat(BetTypes.get("UnionJackPatent")).hasToString("UnionJackPatent")
		assertThat(BetTypes.get("UnionJackRoundRobin")).hasToString("UnionJackRoundRobin")
	}

	@Test
	fun miscBetTypes() {
		assertThat(BetTypes.get("Alphabet")).hasToString("Alphabet")
		assertThat(BetTypes.get("Fido")).hasToString("Fido")
		assertThat(BetTypes.get("Sitter")).hasToString("Sitter")
		assertThat(BetTypes.get("Poly")).hasToString("Poly")
		assertThat(BetTypes.get("Lucky7Bingo")).hasToString("Lucky7Bingo")
		assertThat(BetTypes.get("Mix")).hasToString("Mix")
		assertThat(BetTypes.get("Sundial")).hasToString("Sundial")
		assertThat(BetTypes.get("TripleYankee")).hasToString("TripleYankee")
		assertThat(BetTypes.get("Arkle")).hasToString("Arkle")
		assertThat(BetTypes.get("PermPatent")).hasToString("PermPatent")
		assertThat(BetTypes.get("PermYankee")).hasToString("PermYankee")
	}

	@Test
	fun anyToComeBetTypes() {
		assertThat(BetTypes.get("SingleStakesAbout")).hasToString("SingleStakesAbout")
		assertThat(BetTypes.get("DoubleStakesAbout")).hasToString("DoubleStakesAbout")
		assertThat(BetTypes.get("RoundRobin")).hasToString("RoundRobin")
		assertThat(BetTypes.get("Flag")).hasToString("Flag")
		assertThat(BetTypes.get("SuperFlag")).hasToString("SuperFlag")
		assertThat(BetTypes.get("HeinzFlag")).hasToString("HeinzFlag")
		assertThat(BetTypes.get("SuperHeinzFlag")).hasToString("SuperHeinzFlag")
		assertThat(BetTypes.get("GoliathFlag")).hasToString("GoliathFlag")
		assertThat(BetTypes.get("SingleStakesAbout2")).hasToString("SingleStakesAbout2")
		assertThat(BetTypes.get("SingleStakesAbout20")).hasToString("SingleStakesAbout20")
		assertThat(BetTypes.get("DoubleStakesAbout2")).hasToString("DoubleStakesAbout2")
		assertThat(BetTypes.get("DoubleStakesAbout20")).hasToString("DoubleStakesAbout20")
	}

	@Test
	fun miscAnyToComeBetTypes() {
		assertThat(BetTypes.get("BookiesNightmare")).hasToString("BookiesNightmare")
		assertThat(BetTypes.get("BlanketYankee")).hasToString("BlanketYankee")
		assertThat(BetTypes.get("PermRoundRobin")).hasToString("PermRoundRobin")
		assertThat(BetTypes.get("SingleLap")).hasToString("SingleLap")
		assertThat(BetTypes.get("DoubleLap")).hasToString("DoubleLap")
	}

	@Test
	fun miscAnyToComeSubTypeOfRest() {
		assertThat(BetTypes.get("Rounder")).hasToString("Rounder")
		assertThat(BetTypes.get("Roundabout")).hasToString("Roundabout")
		assertThat(BetTypes.get("RoundTheClock")).hasToString("RoundTheClock")
		assertThat(BetTypes.get("Gyroscope")).hasToString("Gyroscope")
		assertThat(BetTypes.get("Banko")).hasToString("Banko")
		assertThat(BetTypes.get("Comedy")).hasToString("Comedy")
		assertThat(BetTypes.get("LiverpoolRoundTheClock")).hasToString("LiverpoolRoundTheClock")
		assertThat(BetTypes.get("DundeeShuffle")).hasToString("DundeeShuffle")
	}

	@Test
	fun unknownBetType() {
		assertFails { BetTypes.get("Unknown") }
	}
}
