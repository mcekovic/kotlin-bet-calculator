package org.strangeforest.betcalculator

class KotlinTest {

//   @Test
   fun testProperty() {
      val t = TestClass()
      println("Init finished")
      t.a
      t.b
      t.c
      t.d
      TestClass.e
      TestClass.f
      TestClass.g
      println("Next round")
      t.a
      t.b
      t.c
      t.d
      TestClass.e
      TestClass.f
      TestClass.g
   }
}

class TestClass(val a: LazyStuff = LazyStuff(1)) {

   val b = LazyStuff(2)

   val c by lazy { LazyStuff(3) }

   val d
      get() = LazyStuff(4)

   companion object {
      val e = LazyStuff(5)

      val f by lazy { LazyStuff(6) }

      val g
         get() = LazyStuff(7)

   }
}

class LazyStuff(index: Int) {

   init {
      println(index)
   }
}