package com.ahmedmamdouh13.ama.mathquestionapp.util

import androidx.core.text.isDigitsOnly
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.divideSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.firstToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.minusSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.multiplySign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.plusSign
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.secondToResolve
import com.ahmedmamdouh13.ama.mathquestionapp.mapper.OperatorMapper
import java.util.*
import javax.inject.Inject

class MathUtil @Inject constructor() {

    fun parseEquation(equation: String, op1: Int, op2: Int): Double {
        val firstToResolve = getOperatorByOrder(op1, op2, firstToResolve)
        val lastToResolve = getOperatorByOrder(op1, op2, secondToResolve)

        return if (op2 != -1) { // check if there is another operator.
            val firstDimen = solveFirstEquations(equation, lastToResolve, firstToResolve)

            solveFinalEquations(firstDimen, lastToResolve)
        } else
            solveFinalEquations(equation, firstToResolve)

    }

    private fun getOperatorByOrder(op1: Int, op2: Int, order: String): String {
        return when (order) {
            firstToResolve -> {
                if (op1 > op2) OperatorMapper.getOperatorFromFlag(op1)
                else OperatorMapper.getOperatorFromFlag(op2)
            }
            secondToResolve -> {
                if (op1 < op2) OperatorMapper.getOperatorFromFlag(op1)
                else OperatorMapper.getOperatorFromFlag(op2)
            }
            else -> ""
        }
    }


    // solve final raw of sole operator equation.
    private fun solveFinalEquations(firstDimenResult: String, lastToResolve: String): Double {

        var sum = 0.0
        val split = firstDimenResult.split(lastToResolve)

        split.forEachIndexed { i, it ->
            if (i == 0)
                sum = it.toDouble()
            else
                when (lastToResolve) {
                    plusSign -> sum += it.toDouble()
                    minusSign -> sum -= it.toDouble()
                    divideSign -> sum /= it.toDouble()
                    multiplySign -> sum *= it.toDouble()
                }

        }
        return sum

    }

    private fun solveFirstEquations(
        equation: String,
        lastToResolveOperator: String,
        firstToResolveOperator: String
    ): String {

        val equationLastToResolveSplit = equation.split(lastToResolveOperator)
        // remove last step operator to find first to resolve equations.

        val memoryArrayList = arrayListOf<String>()

        // add all numbers and equations to memory to replace equations with result later.
        memoryArrayList.addAll(equationLastToResolveSplit)

        equationLastToResolveSplit.forEachIndexed { i, eq ->
            if (!eq.isDigitsOnly()) { // if not digit then resolve first equation is found.
                val result =
                    getStepResult(eq, firstToResolveOperator) // solve equation based on operator.
                memoryArrayList[i] = result.toString() // replace equation with result.
            }
        }

        //return last step operators in their places.
        return getLastStepEquation(memoryArrayList, lastToResolveOperator)
    }

    private fun getLastStepEquation(list: ArrayList<String>, op: String): String {

        val stringBuilder = StringBuilder()

        list.forEachIndexed { i, s ->
            stringBuilder.append(s)
            if (i < list.size - 1) stringBuilder.append(op)
        }

        return stringBuilder.toString()
    }

    private fun getStepResult(eq: String, firstToResolveOperator: String): Double {
        val equationFirstToResolveSplit = eq.split(firstToResolveOperator)
        return when (firstToResolveOperator) {
            multiplySign -> multiply(equationFirstToResolveSplit)
            divideSign -> divide(equationFirstToResolveSplit)
            plusSign -> plus(equationFirstToResolveSplit)
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