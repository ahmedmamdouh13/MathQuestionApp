package com.ahmedmamdouh13.ama.mathquestionapp.mapper

import com.ahmedmamdouh13.ama.mathquestionapp.Constants

object OperatorMapper {

    fun getOperatorFromFlag(flag: Int): String {
      return  when(flag){
            Constants.multiplySignFlag -> Constants.multiplySign
            Constants.divideSignFlag -> Constants.divideSign
            Constants.plusSignFlag -> Constants.plusSign
            Constants.minusSignFlag -> Constants.minusSign
            else -> ""
        }
    }
}