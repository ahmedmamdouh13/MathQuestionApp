package com.ahmedmamdouh13.ama.mathquestionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.math.MathUtils
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.ahmedmamdouh13.ama.mathquestionapp.service.MathWorker
import com.ahmedmamdouh13.ama.mathquestionapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        mainViewModel.print("1x5x5+5x5", Constants.multiplySignFlag, Constants.plusSignFlag)


        mainViewModel.resultLiveData.observe(this){ list ->
                println(list.outputData.getString("res") + " resultttt")
        }

    }
}