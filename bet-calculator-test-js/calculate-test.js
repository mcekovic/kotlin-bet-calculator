const kotlin = require('../bet-calculator/build/js/node_modules/kotlin/kotlin.js').kotlin;
const bc = require('../bet-calculator/build/js/node_modules/bet-calculator/kotlin/bet-calculator.js').org.strangeforest.betcalculator;

// Calculate Single Bet test

const bet1 = bc.Bet_init(bc.bettypes.Single, '10', new kotlin.collections.ArrayList([
	bc.BetLeg_init('2.0')
]));

console.log(bc.BetCaptureCalculator.calculate(bet1));


// Calculate Accumulator Bet test

const bet2 = bc.Bet_init(bc.bettypes.Accumulator, '10', new kotlin.collections.ArrayList([
	bc.BetLeg_init('2.0'),
	bc.BetLeg_init('3.0')
]));

console.log(bc.BetCaptureCalculator.calculate(bet2));


// Calculate Doubles Bet test

const bet3 = bc.Bet_init(bc.bettypes.Doubles, '10', new kotlin.collections.ArrayList([
	bc.BetLeg_init('2.0'),
	bc.BetLeg_init('3.0'),
	bc.BetLeg_init('4.0')
]));

console.log(bc.BetCaptureCalculator.calculate(bet3));
