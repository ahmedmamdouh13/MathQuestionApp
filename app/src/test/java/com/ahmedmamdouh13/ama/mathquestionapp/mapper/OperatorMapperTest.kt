package com.ahmedmamdouh13.ama.mathquestionapp.mapper

import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import org.junit.Before
import org.junit.Test

class OperatorMapperTest {

    private lateinit var operatorMapper: OperatorMapper

    @Before
    fun setup() {
        operatorMapper = OperatorMapper()
    }


    @Test
    fun `should pass get flag from operator multiply`() {
        //given
        val op = "x"
        val givenFlag = Constants.multiplySignFlag

        //then
        val flag = operatorMapper.getFlagFromOperator(op)
        assert(flag == givenFlag)
    }

    @Test
    fun `should pass get flag from operator minus`() {
        //given
        val op = "-"
        val givenFlag = Constants.minusSignFlag

        //then
        val flag = operatorMapper.getFlagFromOperator(op)
        assert(flag == givenFlag)
    }

    @Test
    fun `should pass get flag from operator divide`() {
        //given
        val op = "/"
        val givenFlag = Constants.divideSignFlag

        //then
        val flag = operatorMapper.getFlagFromOperator(op)
        assert(flag == givenFlag)
    }

    @Test
    fun `should pass get flag from operator plus`() {
        //given
        val op = "+"
        val givenFlag = Constants.plusSignFlag

        //then
        val flag = operatorMapper.getFlagFromOperator(op)
        assert(flag == givenFlag)
    }


    @Test
    fun `should pass get operator plus minus by first to resolve order`() {
        //given
        val op1 = 0
        val op2 = 1
        val order = Constants.firstToResolve
        val givenOperator = Constants.plusSign
        //then
        val operator = operatorMapper.getOperatorByOrder(op1, op2, order)

        assert(givenOperator == operator)
    }

    @Test
    fun `should pass get operator plus minus by second to resolve order`() {
        //given
        val op1 = 0
        val op2 = 1
        val order = Constants.secondToResolve
        val givenOperator = Constants.minusSign
        //then
        val operator = operatorMapper.getOperatorByOrder(op1, op2, order)

        assert(givenOperator == operator)
    }

    @Test
    fun `should pass get operator multiply divide by first to resolve order`() {
        //given
        val op1 = 2
        val op2 = 3
        val order = Constants.firstToResolve
        val givenOperator = Constants.multiplySign
        //then
        val operator = operatorMapper.getOperatorByOrder(op1, op2, order)

        assert(givenOperator == operator)
    }

    @Test
    fun `should pass get operator multiply divide by second to resolve order`() {
        //given
        val op1 = 2
        val op2 = 3
        val order = Constants.secondToResolve
        val givenOperator = Constants.divideSign
        //then
        val operator = operatorMapper.getOperatorByOrder(op1, op2, order)

        assert(givenOperator == operator)
    }

    @Test
    fun `should pass build and get last step equation`() {
        //given
        val op1 = "+"
        val list = arrayListOf("1", "2", "3")
        val givenEquation = "1+2+3"
        //then
        val equation = operatorMapper.getLastStepEquation(list, op1)

        assert(givenEquation == equation)
    }

}