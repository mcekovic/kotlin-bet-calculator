const kotlin = require('../bet-calculator/build/js/node_modules/kotlin/kotlin.js').kotlin;
const bc = require('../bet-calculator/build/js/node_modules/bet-calculator/kotlin/bet-calculator.js').org.strangeforest.betcalculator;

const bet = bc.Bet_init(bc.bettypes.Single, '10', new kotlin.collections.ArrayList([
	bc.BetLeg_init('2.0')
]));

const result = bc.BetCaptureCalculator.calculate(bet);

console.log(result);

