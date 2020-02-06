package com.madhavth.daggermvvmapp

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var check: Int = 0

    @Before
    fun initCheck()
    {
        check = 10
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkTestisTen()
    {
        assertEquals(check, 10)
    }



}
