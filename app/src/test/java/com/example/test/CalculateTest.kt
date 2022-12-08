package com.example.test

import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculateTest : TestCase() {

    private lateinit var calculate: Calculate

    @Before
    override fun setUp(){
        calculate = Calculate()
    }

    @After
    override fun tearDown(){
        //calculate = null
    }

    @Test
    fun addOne(){
        Assert.assertTrue(calculate.addOne() == 1)


        Assert.assertEquals(calculate.addOne(),2)
    }

    @Test
    fun reduceOne(){
        Assert.assertTrue(calculate.reduceOne() == -1)
    }
}