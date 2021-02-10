package com.ahmedmamdouh13.ama.mathquestionapp.mapper

import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import java.util.ArrayList
import javax.inject.Inject

class OperatorMapper @Inject constructor() {

    private fun getOperatorFromFlag(flag: Int): String {
      return  when(flag){
            Constants.multiplySignFlag -> Constants.multiplySign
            Constants.divideSignFlag -> Constants.divideSign
            Constants.plusSignFlag -> Constants.plusSign
            Constants.minusSignFlag -> Constants.minusSign
            else -> ""
        }
    }


    fun getOperatorByOrder(op1: Int, op2: Int, order: String): String {
        return when (order) {
            Constants.firstToResolve -> {
                if (op1 > op2) getOperatorFromFlag(op1)
                else getOperatorFromFlag(op2)
            }
            Constants.secondToResolve -> {
                if (op1 < op2) getOperatorFromFlag(op1)
                else getOperatorFromFlag(op2)
            }
            else -> ""
        }
    }



    fun getLastStepEquation(list: ArrayList<String>, op: String): String {

        val stringBuilder = StringBuilder()

        list.forEachIndexed { i, s ->
            stringBuilder.append(s)
            if (i < list.size - 1) stringBuilder.append(op)
        }

        return stringBuilder.toString()
    }

}