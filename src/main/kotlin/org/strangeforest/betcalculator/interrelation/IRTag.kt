package org.strangeforest.betcalculator.interrelation

import kotlin.collections.ArrayList
import java.util.*
import java.util.regex.*

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

      val EMPTY = IRTag(IRTagSet.EMPTY, IRTagSet.EMPTY, IRTagSet.EMPTY, IRCCTagSet.EMPTY)

      const val OUTCOME_TAGS = "O" // Market Outcome Types
      const val DEP_OUTCOME_TAGS = "D" // Dependent Market Outcome Types
      const val GROUP_TAGS = "G" // Group Relationship Sets
      const val CC_TAGS = "C" // Cause-Consequence Relationship Sets
      const val TYPE_SEPARATOR = ":"
      const val TAG_SEPARATOR = ","
      const val CC_TAGS_SEPARATOR = ">"
      const val TAG_SET_SEPARATOR = ";"


      private val delimiter: Pattern = Pattern.compile("")
      private val typePattern: Pattern = Pattern.compile("[A-Z]")
      private val typeDelimiterPattern: Pattern = Pattern.compile(TYPE_SEPARATOR)
      private val tagPattern: Pattern = Pattern.compile("\\w+|\\*")
      private val tagDelimiterPattern: Pattern = Pattern.compile(TAG_SEPARATOR)
      private val ccTagsDelimiterPattern: Pattern = Pattern.compile(CC_TAGS_SEPARATOR)
      private val delimiterPattern: Pattern = Pattern.compile(TAG_SET_SEPARATOR)

      fun of(tag: String): IRTag {
         var outcomeTagSet: IRTagSet = IRTagSet.EMPTY
         var depOutcomeTagSet: IRTagSet = IRTagSet.EMPTY
         var groupTagSet: IRTagSet = IRTagSet.EMPTY
         var ccTagSet: IRCCTagSet = IRCCTagSet.EMPTY
         val scanner = Scanner(tag)
         scanner.useDelimiter(delimiter)
         while (scanner.hasNext(typePattern)) {
            val setType: String = scanner.findInLine(typePattern)
            scanner.skip(typeDelimiterPattern)
            when (setType) {
               OUTCOME_TAGS -> outcomeTagSet = IRTagSet(nextTags(scanner))
               DEP_OUTCOME_TAGS -> depOutcomeTagSet = IRTagSet(nextTags(scanner))
               GROUP_TAGS -> groupTagSet = IRTagSet(nextTags(scanner))
               CC_TAGS -> ccTagSet = parseCCIRTagSet(scanner)
            }
            if (scanner.hasNext(delimiterPattern))
               scanner.skip(delimiterPattern)
         }
         return IRTag(outcomeTagSet, depOutcomeTagSet, groupTagSet, ccTagSet)
      }

      private fun nextTags(scanner: Scanner): List<String> {
         val tags: MutableList<String> = ArrayList()
         while (scanner.hasNext(tagPattern)) {
            tags.add(scanner.findInLine(tagPattern))
            if (scanner.hasNext(tagDelimiterPattern)) scanner.skip(tagDelimiterPattern)
         }
         return tags
      }

      private fun parseCCIRTagSet(scanner: Scanner): IRCCTagSet {
         val causeTagSet = IRTagSet(nextTags(scanner))
         scanner.skip(ccTagsDelimiterPattern)
         val consequenceTagSet = IRTagSet(nextTags(scanner))
         return IRCCTagSet(causeTagSet, consequenceTagSet)
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
