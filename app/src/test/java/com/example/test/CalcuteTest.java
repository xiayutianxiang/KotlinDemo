package com.example.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CalcuteTest extends TestCase {

    private Calcute mCalcute;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mCalcute = new Calcute();
    }

    @After
    public void tearDown() throws Exception {
        mCalcute = null;
    }

    @Test
    public void testAdd() {
       // assertThat(mCalcute.add(),true);
    }

    @Test
    public void testReduce() {
        mCalcute.reduce();
    }
}