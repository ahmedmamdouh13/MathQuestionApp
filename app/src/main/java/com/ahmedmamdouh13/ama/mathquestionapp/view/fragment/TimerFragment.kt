package com.ahmedmamdouh13.ama.mathquestionapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.databinding.FragmentTimerBinding

class TimerFragment: Fragment(R.layout.fragment_timer) {

    private lateinit var binder: FragmentTimerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder = FragmentTimerBinding.bind(view)



    }
}