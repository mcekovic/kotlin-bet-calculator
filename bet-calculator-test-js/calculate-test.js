const bc = require('../bet-calculator/build/js/node_modules/bet-calculator/kotlin/bet-calculator.js').org.strangeforest.betcalculator;

// Calculate Single Bet test

const bet1 = new bc.Bet('Single', '10', [
	new bc.BetLeg('2.0')
]);

console.log(bc.calculateCapture(bet1));


// Calculate Accumulator Bet test

const bet2 = new bc.Bet('Accumulator', '2', [
	new bc.BetLeg('2.0'),
	new bc.BetLeg('3.0')
]);

console.log(bc.calculateCapture(bet2));


// Calculate Doubles Bet test

const bet3 = new bc.Bet('Doubles', '1', [
	new bc.BetLeg('2.0'),
	new bc.BetLeg('3.0'),
	new bc.BetLeg('4.0')
]);

console.log(bc.calculateCapture(bet3));


// Calculate big Perms Bet test

const bet4 = new bc.Bet('Perms10', '1', [
	new bc.BetLeg('1.1'),
	new bc.BetLeg('1.2'),
	new bc.BetLeg('1.4'),
	new bc.BetLeg('1.6'),
	new bc.BetLeg('1.8'),
	new bc.BetLeg('2.0'),
	new bc.BetLeg('3.0'),
	new bc.BetLeg('4.0'),
	new bc.BetLeg('5.0'),
	new bc.BetLeg('6.0'),
	new bc.BetLeg('1.1'),
	new bc.BetLeg('1.2'),
	new bc.BetLeg('1.4'),
	new bc.BetLeg('1.6'),
	new bc.BetLeg('1.8'),
	new bc.BetLeg('2.0'),
	new bc.BetLeg('3.0'),
	new bc.BetLeg('4.0'),
	new bc.BetLeg('5.0'),
	new bc.BetLeg('6.0'),
]);

const t0 = new Date();
console.log(bc.calculateCapture(bet4));
console.log(new Date() - t0);
