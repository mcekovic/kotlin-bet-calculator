package org.strangeforest.betcalculator.util

internal class Combinations<T>(private val items: List<T>, private val length: Int) : Iterator<List<T>> {

   private val variableCount = items.size - length
   private val indexes: IntArray
   private var hasNext = items.isNotEmpty()

   init {
      require(length >= 0) { "length must be positive"}
      require(items.size >= length) { "items size must be greater or equal to length"}
      indexes = IntArray(length)
      for (i in 0 until length)
         indexes[i] = i
   }

   override fun hasNext(): Boolean {
      return hasNext
   }

   override fun next(): List<T> {
      if (!hasNext)
         throw NoSuchElementException()
      val next = ArrayList<T>(length)
      for (i in 0 until length)
         next.add(items[indexes[i]])
      moveIndex()
      return next
   }

   private fun moveIndex() {
      val i = nextDiffIndex()
      if (i >= 0) {
         indexes[i]++
         for (j in i + 1 until length)
            indexes[j] = indexes[j - 1] + 1
      }
      else
         hasNext = false
   }

   private fun nextDiffIndex(): Int {
      for (i in length - 1 downTo 0) {
         if (indexes[i] < variableCount + i)
            return i
      }
      return -1
   }
}

fun <T> combinations(items: List<T>, length: Int): Sequence<List<T>> = Combinations(items, length).asSequence()
