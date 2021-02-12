package com.ahmedmamdouh13.ama.mathquestionapp.usecase

import android.service.autofill.CharSequenceTransformation
import android.text.TextUtils
import androidx.core.text.isDigitsOnly
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.firstToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.secondToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.mapper.OperatorMapper
import com.ahmedmamdouh13.ama.mathquestionapp.util.EquationValidation
import com.ahmedmamdouh13.ama.mathquestionapp.util.MyMathUtil
import javax.inject.Inject

class MathUseCase @Inject constructor(private val mapper: OperatorMapper, private val util: MyMathUtil, private val equationValidation: EquationValidation) {



    fun parseEquation(equation: String, op1: Int, op2: Int): Double {

        val firstToResolve = mapper.getOperatorByOrder(op1, op2, firstToResolve)

        return if (op2 != -1) { // check if there is another operator.
            val lastToResolve = mapper.getOperatorByOrder(op1, op2, secondToResolve)

            val firstDimen = solveFirstEquations(equation, lastToResolve, firstToResolve)

            solveFinalEquations(firstDimen, lastToResolve)
        } else
            solveFinalEquations(equation, firstToResolve)

    }


    // solve final raw of sole operator equation.
    private fun solveFinalEquations(firstDimenResult: String, lastToResolve: String): Double {
        return util.getStepResult(firstDimenResult, lastToResolve)
    }

    private fun solveFirstEquations(equation: String, lastToResolveOperator: String, firstToResolveOperator: String): String {

        val equationLastToResolveSplit = equation.split(lastToResolveOperator)
        // remove last step operator to find "first to resolve" equations.

        val memoryArrayList = arrayListOf<String>()

        // add all numbers and equations to memory to replace equations with result later.
        memoryArrayList.addAll(equationLastToResolveSplit)

        equationLastToResolveSplit.forEachIndexed { i, eq ->
            if (equationValidation.validateEquation(eq)) { // if is valid equation then "resolve first equation" is found.
                val result = util.getStepResult(eq, firstToResolveOperator) // solve equation based on operator.
                memoryArrayList[i] = result.toString() // replace equation with result.
            }
        }

        //return last step operators in their places.
        return mapper.getLastStepEquation(memoryArrayList, lastToResolveOperator)
    }

}