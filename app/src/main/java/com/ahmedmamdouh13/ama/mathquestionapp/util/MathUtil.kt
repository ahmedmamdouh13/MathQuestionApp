package com.ahmedmamdouh13.ama.mathquestionapp.util

import androidx.core.text.isDigitsOnly
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.divideSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.firstToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.minusSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.multiplySign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.plusSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.secondToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.mapper.OperatorMapper
import javax.inject.Inject

class MathUtil @Inject constructor(private val mapper: OperatorMapper) {



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
        return getStepResult(firstDimenResult, lastToResolve)
    }

    private fun solveFirstEquations(equation: String, lastToResolveOperator: String, firstToResolveOperator: String): String {

        val equationLastToResolveSplit = equation.split(lastToResolveOperator)
        // remove last step operator to find "first to resolve" equations.

        val memoryArrayList = arrayListOf<String>()

        // add all numbers and equations to memory to replace equations with result later.
        memoryArrayList.addAll(equationLastToResolveSplit)

        equationLastToResolveSplit.forEachIndexed { i, eq ->
            if (!eq.isDigitsOnly()) { // if not digit then "resolve first equation" is found.
                val result = getStepResult(eq, firstToResolveOperator) // solve equation based on operator.
                memoryArrayList[i] = result.toString() // replace equation with result.
            }
        }

        //return last step operators in their places.
        return mapper.getLastStepEquation(memoryArrayList, lastToResolveOperator)
    }

    private fun getStepResult(eq: String, firstToResolveOperator: String): Double {
        val equationFirstToResolveSplit = eq.split(firstToResolveOperator)
        return when (firstToResolveOperator) {
            multiplySign -> multiply(equationFirstToResolveSplit)
            divideSign -> divide(equationFirstToResolveSplit)
            plusSign -> plus(equationFirstToResolveSplit)
            minusSign -> minus(equationFirstToResolveSplit)
            else -> 1.0
        }

    }

    private fun plus(equationFirstToResolveSplit: List<String>): Double {
        var result = 0.0
        equationFirstToResolveSplit
            .forEach {
                result += it.toDouble()
            }
        return result
    }
    private fun minus(equationFirstToResolveSplit: List<String>): Double {
        var result = 0.0
        equationFirstToResolveSplit
            .forEachIndexed { i, it ->
                if (i == 0)
                    result = it.toDouble()
                else
                result -= it.toDouble()
            }
        return result
    }

    private fun divide(equationFirstToResolveSplit: List<String>): Double {
        var result = 1.0
        equationFirstToResolveSplit
            .forEachIndexed { x, it ->
                if (x == 0)
                    result = it.toDouble()
                else
                    result /= it.toDouble()
            }
        return result
    }

    private fun multiply(equationFirstToResolveSplit: List<String>): Double {
        var result = 1.0
        equationFirstToResolveSplit
            .forEach {
                result *= it.toDouble()
            }
        return result
    }
}