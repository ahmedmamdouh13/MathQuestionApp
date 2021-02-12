package com.ahmedmamdouh13.ama.mathquestionapp.util

import com.ahmedmamdouh13.ama.mathquestionapp.usecase.MathUseCase
import io.mockk.MockKAnnotations
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MyMathUtilTest{

    lateinit var myMathUtil: MyMathUtil

    @Before
    fun setup() {
        myMathUtil = MyMathUtil()
    }

    @Test
    fun `should pass multiply equation`(){
        //given
        val equation = "2x1x2x3"
        val op = "x"
        val answer = 12.0

        //then
     val result = myMathUtil.getStepResult(equation, op)

        assert(result == answer)
    }

    @Test
    fun `should pass plus equation`(){
        //given
        val equation = "2+1+2+3"
        val op = "+"
        val answer = 8.0

        //then
     val result = myMathUtil.getStepResult(equation, op)

        assert(result == answer)
    }

    @Test
    fun `should pass minus equation`(){
        //given
        val equation = "2-1-2-3"
        val op = "-"
        val answer = -4.0

        //then
     val result = myMathUtil.getStepResult(equation, op)

        assert(result == answer)
    }

    @Test
    fun `should pass divide equation`(){
        //given
        val equation = "12/3/4"
        val op = "/"
        val answer = 1.0

        //then
     val result = myMathUtil.getStepResult(equation, op)

        assert(result == answer)
    }

    @Test
    fun `should pass critical condition`(){
        //given
        val equation = "12&3&4"
        val op = "&"
        val answer = 1.0

        //then
     val result = myMathUtil.getStepResult(equation, op)

        assert(result == answer)
    }

}