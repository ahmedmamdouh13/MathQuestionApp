package com.ahmedmamdouh13.ama.mathquestionapp.util

import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import javax.inject.Inject

class EquationValidation @Inject constructor() {

    fun validateEquation(equation: String): Boolean {
        return (equation.contains(Constants.plusSign) ||
                equation.contains(Constants.multiplySign) ||
                equation.contains(Constants.divideSign) ||
                equation.contains(Constants.minusSign))
                && equation.last().isDigit()
    }


}