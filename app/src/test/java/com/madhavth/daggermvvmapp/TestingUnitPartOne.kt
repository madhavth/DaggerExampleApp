package com.madhavth.daggermvvmapp

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class TestingUnitPartOne
{


    var a = 10

    var myMap = mapOf<String,Int>("One" to 1, "Two" to 2)

    @Before
    fun initA()
    {
        a= 20
    }

   @Test
   fun checkMap()
   {
       assertEquals(myMap, mapOf<String,Int>())
   }

    @Test
    fun checkAgain()
    {
        assertEquals(a, 20)
    }

}