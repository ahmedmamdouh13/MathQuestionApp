package com.ahmedmamdouh13.ama.mathquestionapp.viewmodel

import android.app.Activity
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
import com.ahmedmamdouh13.ama.mathquestionapp.util.LocationUtil
import com.ahmedmamdouh13.ama.mathquestionapp.util.MyPermissions
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val locationUtil: LocationUtil
) : ViewModel() {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    var _resultLiveData: LiveData<List<WorkInfo>>

    private var jobIdCnt = 0

    private val equationModelLiveData: MutableLiveData<EquationModel> = MutableLiveData()
    val _equationModelLiveData: LiveData<EquationModel> = equationModelLiveData


    private val locationInfoLiveData: MutableLiveData<String> = MutableLiveData()
    val _locationInfoLiveData: LiveData<String> = locationInfoLiveData

    private val tag = "math"


    init {
        _resultLiveData = workManager.getWorkInfosByTagLiveData(tag)
        println("$jobIdCnt  recreated?!")
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



        workManager.enqueue(workRequest)

        _resultLiveData = workManager.getWorkInfosByTagLiveData(tag)


        displayEquation(jobIdCnt, equation, duration)


    }

    private fun displayEquation(jobIdCnt: Int, equation: String, duration: Long) {
        val equationModel = EquationModel().apply {
            this.jobId = jobIdCnt
            this.equation = equation
            this.delayed = duration
        }
        equationModelLiveData.value = equationModel
    }

    fun cancelFinishedJob(uuid: UUID) {
        workManager.cancelWorkById(uuid)
    }

    fun cancelAllJobs() {
        workManager.cancelAllWork()
    }

    fun getLocationInfo(context: Activity) {

        if (!MyPermissions.isLocationPermissionGranted(context)) {
            MyPermissions.requestPermissions(context)
        } else {
            locationUtil.getLastKnownLocation(context) {
                val loc = "Longitude: " + it?.longitude.toString() + ", Latitude: " + it?.latitude.toString()

                locationInfoLiveData.value = loc
            }
        }

    }
}