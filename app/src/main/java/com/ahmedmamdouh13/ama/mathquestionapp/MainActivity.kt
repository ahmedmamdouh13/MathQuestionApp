package com.ahmedmamdouh13.ama.mathquestionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        mainViewModel.print("something")

        val workRequest = OneTimeWorkRequest.Builder(MathWorker::class.java)
                .setInitialDelay(5L, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this).enqueue(workRequest)

    }
}