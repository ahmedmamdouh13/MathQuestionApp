package com.ahmedmamdouh13.ama.mathquestionapp.view

import androidx.fragment.app.FragmentManager
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.CalculatorFragment
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.HomeFragment
import com.ahmedmamdouh13.ama.mathquestionapp.view.fragment.TimerFragment

object Navigate {
    fun toHomeFragment(supportFragmentManager: FragmentManager, container: Int) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(container, HomeFragment(),"home")
            .commit()
    }

    fun toCalculatorFragment(supportFragmentManager: FragmentManager, container: Int) {
        supportFragmentManager.beginTransaction()
            .replace(container, CalculatorFragment(),"calc")
            .addToBackStack("calc")
            .commit()
    }

    fun toTimerFragment(supportFragmentManager: FragmentManager, container: Int) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(container, TimerFragment(),"timer")
            .addToBackStack("timer")
            .commit()
    }

}