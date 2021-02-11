package com.ahmedmamdouh13.ama.mathquestionapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.EQUATION_KEY
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.op1Key
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.op2Key
import com.ahmedmamdouh13.ama.mathquestionapp.model.EquationModel
import com.ahmedmamdouh13.ama.mathquestionapp.service.MathWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    var _resultLiveData: LiveData<List<WorkInfo>>

    private var jobIdCnt = 0

    private val equationModelLiveData: MutableLiveData<LinkedHashMap<Int, EquationModel>> = MutableLiveData()
    val _equationModelLiveData: MutableLiveData<LinkedHashMap<Int, EquationModel>> = equationModelLiveData

    private var equationMap: LinkedHashMap<Int, EquationModel> = linkedMapOf()
    private val tag = "math"


    init {

//        workManager.cancelAllWork()
//        pruneFinishedJobs()
        _resultLiveData = workManager.getWorkInfosByTagLiveData(tag)
//        workManager.cancelAllWorkByTag("math")
    }

    fun scheduleJob(equation: String, op1: Int, op2: Int, duration: Long = 1L) {

        workManager.pruneWork()

        jobIdCnt++

        val equationData = Data.Builder().putString(EQUATION_KEY, equation)
            .putInt(op1Key, op1)
            .putInt(op2Key, op2)
            .putInt(Constants.jobId, jobIdCnt)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MathWorker::class.java)
            .addTag(tag)
            .setInitialDelay(duration, TimeUnit.SECONDS)
            .setInputData(equationData)
            .build()


        val id = workRequest.id

        workManager.enqueue(workRequest)

        _resultLiveData = workManager.getWorkInfosByTagLiveData(tag)


        displayEquation(jobIdCnt, equation, duration)


    }

    private fun displayEquation(jobIdCnt: Int, equation: String, duration: Long) {
        equationMap[jobIdCnt] = EquationModel().apply {
            this.jobId = jobIdCnt
            this.equation = equation
            this.delayed = duration
        }
        equationModelLiveData.value = equationMap
    }

    fun cancelFinishedJob(uuid: UUID) {
        workManager.cancelWorkById(uuid)
    }

    fun cancelAllJobs() {
        workManager.cancelAllWork()
    }
}