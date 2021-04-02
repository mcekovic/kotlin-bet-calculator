package org.strangeforest.betcalculator.interrelation

import assertk.*
import assertk.assertions.support.*

internal fun Assert<IrDescriptor>.isInterrelatedWith(descriptor: IrDescriptor) = given { actual ->
   if (IrDetector.areInterrelated(actual, descriptor).isInterrelated()) return
   expected("$actual to be interrelated with $descriptor")
}

internal fun Assert<IrDescriptor>.isNotInterrelatedWith(descriptor: IrDescriptor) = given { actual ->
   if (!IrDetector.areInterrelated(actual, descriptor).isInterrelated()) return
   expected("$actual to not be interrelated with $descriptor")
}
