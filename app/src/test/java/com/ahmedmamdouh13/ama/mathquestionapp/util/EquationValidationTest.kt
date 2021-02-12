package com.ahmedmamdouh13.ama.mathquestionapp.util

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class EquationValidationTest{

    private lateinit var equationValidation: EquationValidation

    @Before
    fun setup(){
        equationValidation = EquationValidation()
    }

    @Test
    fun `should pass true validate equation`(){
        //given
        val equation = "9x9x8x4"
        val givenIsValid = true
        //then
        val isValid = equationValidation.validateEquation(equation)

        assert(isValid == givenIsValid)

    }

    @Test
    fun `should pass false no operator validate equation`(){
        //given
        val equation = "9984"
        val givenIsValid = false
        //then
        val isValid = equationValidation.validateEquation(equation)

        assert(isValid == givenIsValid)

    }

    @Test
    fun `should pass false last item is operator validate equation`(){
        //given
        val equation = "9x9x8x"
        val givenIsValid = false
        //then
        val isValid = equationValidation.validateEquation(equation)

        assert(isValid == givenIsValid)

    }
}