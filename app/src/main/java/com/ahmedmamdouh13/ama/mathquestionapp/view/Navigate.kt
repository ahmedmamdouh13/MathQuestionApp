package com.ahmedmamdouh13.ama.mathquestionapp.view

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.CalculatorFragment
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.TimerFragment

object Navigate {

    fun toCalculatorFragment(supportFragmentManager: FragmentManager, container: Int) {
       val calculatorFragment = CalculatorFragment()

        supportFragmentManager.beginTransaction()
            .replace(container, calculatorFragment, "calc")
            .commitNow()
    }

    fun toTimerFragment(
        supportFragmentManager: FragmentManager,
        container: Int,
        equation: String,
        op1: Int,
        op2: Int
    ) {
//        timerFragment = TimerFragment(equation, op1, op2)
       val timerFragment = TimerFragment()

        val bundle = Bundle()
            .apply {
                putString("equation", equation)
                putInt("op1", op1)
                putInt("op2", op2)
            }
        timerFragment.arguments = bundle


        supportFragmentManager.beginTransaction()
            .replace(container, TimerFragment::class.java, bundle, "timer")
            .commitNow()
    }

//    fun clear(supportFragmentManager: FragmentManager) {
//        supportFragmentManager.beginTransaction().remove(timerFragment)
//        supportFragmentManager.beginTransaction().remove(calculatorFragment)
//    }

}