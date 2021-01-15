package org.strangeforest.betcalculator.interrelation

data class IRCCTagSet(val causeTags: IRTagSet, val consequenceTags: IRTagSet) {

   infix fun ccIntersects(ccTagSet: IRCCTagSet): Boolean = causeTags intersects ccTagSet.consequenceTags

   operator fun plus(tagSet: IRCCTagSet): IRCCTagSet {
      return IRCCTagSet(causeTags + tagSet.causeTags, consequenceTags + tagSet.consequenceTags)
   }

   val empty: Boolean
      get() = causeTags.empty && consequenceTags.empty


   // Any

   override fun toString(): String =
      causeTags.tags.joinToString(IRTag.TAG_SEPARATOR) + IRTag.CC_TAGS_SEPARATOR + consequenceTags.tags.joinToString(IRTag.TAG_SEPARATOR)

   companion object {
      val EMPTY = IRCCTagSet(IRTagSet.EMPTY, IRTagSet.EMPTY)
   }
}
