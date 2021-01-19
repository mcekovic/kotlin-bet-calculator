package org.strangeforest.betcalculator.interrelation

data class IrCcTagSet(val causeTags: IrTagSet, val consequenceTags: IrTagSet) {

   infix fun ccIntersects(ccTagSet: IrCcTagSet): Boolean = causeTags intersects ccTagSet.consequenceTags

   operator fun plus(tagSet: IrCcTagSet): IrCcTagSet {
      return IrCcTagSet(causeTags + tagSet.causeTags, consequenceTags + tagSet.consequenceTags)
   }

   val empty: Boolean
      get() = causeTags.empty && consequenceTags.empty


   // Any

   override fun toString(): String =
      causeTags.tags.joinToString(IrTag.TAG_SEPARATOR) + IrTag.CC_TAGS_SEPARATOR + consequenceTags.tags.joinToString(IrTag.TAG_SEPARATOR)

   companion object {
      val EMPTY = IrCcTagSet(IrTagSet.EMPTY, IrTagSet.EMPTY)
   }
}
