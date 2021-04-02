package org.strangeforest.betcalculator.interrelation

internal class IrTagSet(val tags: Collection<String>) {

   constructor(vararg tags: String) : this(listOf<String>(*tags))

   infix fun intersects(tagSet: IrTagSet): Boolean {
      for (tag in tagSet.tags) {
         if (ALL == tag || tags.contains(ALL) || tags.contains(tag))
            return true
      }
      return false
   }

   operator fun plus(other: IrTagSet): IrTagSet {
      val set = toSet()
      set.addAll(other.tags)
      return IrTagSet(set)
   }

   val empty: Boolean
      get() = tags.isEmpty()

   private fun toSet(): MutableSet<String> = HashSet(tags)


   // Any

   override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other !is IrTagSet) return false
      return toSet() == other.toSet()
   }

   override fun hashCode(): Int = toSet().hashCode()

   override fun toString(): String = tags.joinToString(IrTag.TAG_SEPARATOR)


   // Factory

   companion object {

      val EMPTY = IrTagSet(emptySet())
      const val ALL = "*"
   }
}
