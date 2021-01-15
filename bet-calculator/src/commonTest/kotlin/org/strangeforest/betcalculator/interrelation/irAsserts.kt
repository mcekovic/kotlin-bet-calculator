package org.strangeforest.betcalculator.interrelation

import assertk.*
import assertk.assertions.support.*

fun Assert<IRDescriptor>.isInterrelatedWith(descriptor: IRDescriptor) = given { actual ->
   if (IRDetector.areInterrelated(actual, descriptor).isInterrelated()) return
   expected("$actual to be interrelated with $descriptor")
}

fun Assert<IRDescriptor>.isNotInterrelatedWith(descriptor: IRDescriptor) = given { actual ->
   if (!IRDetector.areInterrelated(actual, descriptor).isInterrelated()) return
   expected("$actual to not be interrelated with $descriptor")
}
