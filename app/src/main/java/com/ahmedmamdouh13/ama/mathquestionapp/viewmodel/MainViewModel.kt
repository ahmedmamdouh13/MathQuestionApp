package com.ahmedmamdouh13.ama.mathquestionapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.EQUATION_KEY
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.op1Key
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.op2Key
import com.ahmedmamdouh13.ama.mathquestionapp.service.MathWorker
import com.ahmedmamdouh13.ama.mathquestionapp.util.MathUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    lateinit var resultLiveData: LiveData<WorkInfo>

    fun print(s: String, op1: Int, op2: Int) {

        workManager.cancelAllWork()
        workManager.cancelAllWorkByTag("math")

        val equationData = Data.Builder().putString(EQUATION_KEY, s)
            .putInt(op1Key, op1)
            .putInt(op2Key, op2)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MathWorker::class.java)
            .addTag("math")
            .setInitialDelay(5L, TimeUnit.SECONDS)
            .setInputData(equationData)
            .build()


        val id = workRequest.id

        workManager.enqueue(workRequest)

        resultLiveData = workManager.getWorkInfoByIdLiveData(id)


    }
}