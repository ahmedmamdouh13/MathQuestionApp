package com.ahmedmamdouh13.ama.mathquestionapp.util

import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import javax.inject.Inject

class MyMathUtil @Inject constructor() {




    fun getStepResult(eq: String, operator: String): Double {
        val equationFirstToResolveSplit = eq.split(operator)
        return when (operator) {
            Constants.multiplySign -> multiply(equationFirstToResolveSplit)
            Constants.divideSign -> divide(equationFirstToResolveSplit)
            Constants.plusSign -> plus(equationFirstToResolveSplit)
            Constants.minusSign -> minus(equationFirstToResolveSplit)
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