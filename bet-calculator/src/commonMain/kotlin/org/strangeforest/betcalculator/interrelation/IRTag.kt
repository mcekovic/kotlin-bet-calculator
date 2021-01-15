package org.strangeforest.betcalculator.interrelation

data class IRTag(
   val outcomeTagSet: IRTagSet = IRTagSet.EMPTY,
   val depOutcomeTagSet: IRTagSet = IRTagSet.EMPTY,
   val groupTagSet: IRTagSet = IRTagSet.EMPTY,
   val ccTagSet: IRCCTagSet = IRCCTagSet.EMPTY
) {

   infix fun outcomeMatchedWith(tag: IRTag): Boolean =
      outcomeTagSet intersects tag.outcomeTagSet

   infix fun groupInterrelatedWith(tag: IRTag): Boolean =
      groupTagSet intersects tag.groupTagSet && outcomeTagSet intersects tag.outcomeTagSet

   infix fun ccInterrelatedWith(tag: IRTag): Boolean =
      ccTagSet ccIntersects tag.ccTagSet && outcomeTagSet intersects tag.depOutcomeTagSet

   operator fun plus(other: IRTag): IRTag =
      IRTag(outcomeTagSet + other.outcomeTagSet, depOutcomeTagSet + other.depOutcomeTagSet, groupTagSet + other.groupTagSet, ccTagSet + other.ccTagSet)


   // Any

   override fun toString(): String {
      val sb = StringBuilder()
      if (!outcomeTagSet.empty) appendTagSet(sb, OUTCOME_TAGS, outcomeTagSet)
      if (!depOutcomeTagSet.empty) appendTagSet(sb, DEP_OUTCOME_TAGS, depOutcomeTagSet)
      if (!groupTagSet.empty) appendTagSet(sb, GROUP_TAGS, groupTagSet)
      if (!ccTagSet.empty) appendTagSet(sb, CC_TAGS, ccTagSet)
      return sb.toString()
   }

   companion object {

      val EMPTY = IRTag()

      const val OUTCOME_TAGS = "O" // Market Outcome Types
      const val DEP_OUTCOME_TAGS = "D" // Dependent Market Outcome Types
      const val GROUP_TAGS = "G" // Group Relationship Sets
      const val CC_TAGS = "C" // Cause-Consequence Relationship Sets
      const val TAG_SET_SEPARATOR = ";"
      const val TYPE_SEPARATOR = ":"
      const val TAG_SEPARATOR = ","
      const val CC_TAGS_SEPARATOR = ">"

      fun of(tag: String): IRTag {
         var outcomeTagSet = IRTagSet.EMPTY
         var depOutcomeTagSet = IRTagSet.EMPTY
         var groupTagSet = IRTagSet.EMPTY
         var ccTagSet = IRCCTagSet.EMPTY
         val tagSets = tag.split(TAG_SET_SEPARATOR).filter(String::isNotEmpty)
         for (tagSet in tagSets) {
            val tagSetParts = tagSet.split(TYPE_SEPARATOR)
            if (tagSetParts.size < 2)
               throw IllegalArgumentException("Invalid tag: $tag")
            when (val tagSetType = tagSetParts[0]) {
               OUTCOME_TAGS -> outcomeTagSet = parseTagSet(tagSetParts[1])
               DEP_OUTCOME_TAGS -> depOutcomeTagSet = parseTagSet(tagSetParts[1])
               GROUP_TAGS -> groupTagSet = parseTagSet(tagSetParts[1])
               CC_TAGS -> ccTagSet = parseCCTagSet(tagSetParts[1])
               else -> throw IllegalArgumentException("Unknown tag type: $tagSetType")
            }
         }
         return IRTag(outcomeTagSet, depOutcomeTagSet, groupTagSet, ccTagSet)
      }

      private fun parseTagSet(tags: String) = IRTagSet(tags.split(TAG_SEPARATOR).filter(String::isNotEmpty))

      private fun parseCCTagSet(ccTags: String): IRCCTagSet {
         val ccTagSetParts = ccTags.split(CC_TAGS_SEPARATOR)
         if (ccTagSetParts.size < 2)
            throw IllegalArgumentException("Invalid CC tag: $ccTags")
         return IRCCTagSet(parseTagSet(ccTagSetParts[0]), parseTagSet(ccTagSetParts[1]))
      }

      private fun appendTagSet(sb: StringBuilder, tagSetType: String, tagSet: Any) {
         if (sb.isNotEmpty())
            sb.append(TAG_SET_SEPARATOR)
         sb.append(tagSetType)
         sb.append(TYPE_SEPARATOR)
         sb.append(tagSet)
      }
   }
}
