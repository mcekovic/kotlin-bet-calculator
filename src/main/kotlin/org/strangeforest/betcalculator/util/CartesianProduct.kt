package org.strangeforest.betcalculator.util

internal class CartesianProduct<T>(private val items: List<List<T>>) : Iterator<List<T>> {

   private val length = items.size
   private var index = 0
   private var nextIndex = length - 1
   private val indexes = IntArray(length)
   private var hasEmptyList = items.any(List<T>::isEmpty)

   override fun hasNext(): Boolean {
      return !hasEmptyList && nextIndex >= 0
   }

   override fun next(): List<T> {
      if (!hasNext())
         throw NoSuchElementException()
      if (index == 0)
         return generateNext()
      indexes[nextIndex]++
      for (i in nextIndex + 1 until length)
         indexes[i] = 0
      return generateNext()
   }

   private fun generateNext(): List<T> {
      val next = ArrayList<T>()
      for (i in 0 until length)
         next.add(items[i][indexes[i]])
      nextIndex = length - 1
      while (nextIndex >= 0 && indexes[nextIndex] + 1 >= items[nextIndex].size)
         nextIndex--
      index++
      return next
   }
}

fun <T> cartesianProduct(items: List<List<T>>): Sequence<List<T>> = CartesianProduct(items).asSequence()
