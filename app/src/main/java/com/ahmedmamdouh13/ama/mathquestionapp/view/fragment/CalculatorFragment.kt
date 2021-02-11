package com.ahmedmamdouh13.ama.mathquestionapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.R
import com.ahmedmamdouh13.ama.mathquestionapp.databinding.FragmentCalculatorBinding
import com.ahmedmamdouh13.ama.mathquestionapp.view.Navigate
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment: Fragment(R.layout.fragment_calculator) {

    lateinit var binder: FragmentCalculatorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder = FragmentCalculatorBinding.bind(view)

        val mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binder.nextFabFragmentcalculator
            .setOnClickListener {
                Navigate.toTimerFragment(parentFragmentManager, R.id.main_container)
            }



        mainViewModel.scheduleJob(
            "1x5x5-5x5",
            Constants.multiplySignFlag,
            Constants.minusSignFlag,
            8
        )
        mainViewModel.scheduleJob(
            "1x5x5-5x5",
            Constants.multiplySignFlag,
            Constants.minusSignFlag,
            10
        )
        mainViewModel.scheduleJob(
            "1x5x5-5x5",
            Constants.multiplySignFlag,
            Constants.minusSignFlag,
            20
        )
        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+54+4+4x5x5x5x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            25
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+546+54+4+4x5x5x5x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            10
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            15
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+546+54+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            11
        )

        mainViewModel.scheduleJob(
            "5+4x5+6+45+5+65+65+4x5",
            Constants.multiplySignFlag,
            Constants.plusSignFlag,
            13
        )

        mainViewModel.scheduleJob(
            "1x5x5/5x5",
            Constants.multiplySignFlag,
            Constants.divideSignFlag,
            1
        )



    }
}