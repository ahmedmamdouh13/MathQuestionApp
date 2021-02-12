package com.ahmedmamdouh13.ama.mathquestionapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.databinding.FragmentTimerBinding
import com.ahmedmamdouh13.ama.mathquestionapp.view.Navigate
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment(private val equation: String, private val op1: Int, private val op2: Int) :
    Fragment(R.layout.fragment_timer) {

    private lateinit var binder: FragmentTimerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder = FragmentTimerBinding.bind(view)
        val mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        binder.secondsTimerfragment.fifteenButton.setOnClickListener {
            mainViewModel.scheduleJob(equation,op1,op2,15)
            Navigate.clear(parentFragmentManager)
        }

        binder.secondsTimerfragment.tenButton.setOnClickListener {
            mainViewModel.scheduleJob(equation,op1,op2,10)
            Navigate.clear(parentFragmentManager)
        }

        binder.secondsTimerfragment.fiveButton.setOnClickListener {
            mainViewModel.scheduleJob(equation,op1,op2,5)
            Navigate.clear(parentFragmentManager)
        }

        binder.secondsTimerfragment.thirtyButton.setOnClickListener {
            mainViewModel.scheduleJob(equation,op1,op2,30)
            Navigate.clear(parentFragmentManager)
        }

    }
}