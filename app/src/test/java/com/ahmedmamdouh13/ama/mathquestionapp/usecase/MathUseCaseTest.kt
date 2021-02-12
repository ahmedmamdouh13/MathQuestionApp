package com.ahmedmamdouh13.ama.mathquestionapp.usecase

import android.text.TextUtils
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.firstToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.secondToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.mapper.OperatorMapper
import com.ahmedmamdouh13.ama.mathquestionapp.util.EquationValidation
import com.ahmedmamdouh13.ama.mathquestionapp.util.MyMathUtil
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Test


class MathUseCaseTest {

    @MockK
    lateinit var mapper: OperatorMapper

    @MockK
    lateinit var myMathUtil: MyMathUtil

    @MockK
    lateinit var equationValidation: EquationValidation

    lateinit var mathUseCase: MathUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mathUseCase = MathUseCase(mapper, myMathUtil, equationValidation)
    }

    @Test
    fun `should pass parsing 2 step equation and return answer`() {
        //given
        val equation1 = "2x4+8" // = 16
        val op1 = Constants.multiplySignFlag
        val op2 = Constants.plusSignFlag
        val answer1 = 16.0
        val memoryList = arrayListOf<String>().apply {
            this.add("8.0")
            this.add("8")

        }


        //when
        every {
            mapper.getOperatorByOrder(op1, op2, firstToResolve)
        } returns Constants.multiplySign

        every {
            mapper.getOperatorByOrder(op1, op2, secondToResolve)
        } returns Constants.plusSign

        every {
            myMathUtil.getStepResult("2x4", Constants.multiplySign)
        } returns 8.0

        every {
            myMathUtil.getStepResult("8+8", Constants.plusSign)
        } returns answer1


        every {
            mapper.getLastStepEquation(memoryList, Constants.plusSign)
        } returns "8+8"

        every {
            equationValidation.validateEquation("2x4")
        } returns true


        every {
            equationValidation.validateEquation("8")
        } returns false


        //then
        val answer = mathUseCase.parseEquation(equation1, op1, op2)

        assert(answer == answer1)
    }


    @Test
    fun `should pass parsing 1 step equation and return answer`() {
        //given
        val equation1 = "2x4x8x9" // = 576
        val op1 = Constants.multiplySignFlag
        val answer1 = 576.0



        //when
        every {
            mapper.getOperatorByOrder(op1, -1, firstToResolve)
        } returns Constants.multiplySign

        every {
            myMathUtil.getStepResult("2x4x8x9", Constants.multiplySign)
        } returns answer1


        //then
        val answer = mathUseCase.parseEquation(equation1, op1, -1)

        assert(answer == answer1)
    }


}