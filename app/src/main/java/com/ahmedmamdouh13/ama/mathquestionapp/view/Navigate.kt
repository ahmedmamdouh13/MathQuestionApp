package com.ahmedmamdouh13.ama.mathquestionapp.view

import androidx.fragment.app.FragmentManager
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.CalculatorFragment
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.TimerFragment

object Navigate {
    private lateinit var timerFragment: TimerFragment
    private val calculatorFragment = CalculatorFragment()

    fun toCalculatorFragment(supportFragmentManager: FragmentManager, container: Int) {
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
        timerFragment = TimerFragment(equation, op1, op2)


        supportFragmentManager.beginTransaction()
            .replace(container, timerFragment, "timer")
            .commitNow()
    }

    fun clear(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction().remove(timerFragment)
        supportFragmentManager.beginTransaction().remove(calculatorFragment)
    }

}