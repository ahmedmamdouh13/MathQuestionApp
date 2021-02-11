package com.ahmedmamdouh13.ama.mathquestionapp.service

import android.content.Context
import android.os.Debug
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ahmedmamdouh13.ama.mathquestionapp.Constants
import com.ahmedmamdouh13.ama.mathquestionapp.Constants.EQUATION_KEY
import com.ahmedmamdouh13.ama.mathquestionapp.util.MathUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MathWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val util: MathUtil
) : Worker(context, workerParameters) {

    private val parameters = workerParameters

    override fun doWork(): Result {

        val equation = parameters.inputData.getString(EQUATION_KEY)
        val op1 = parameters.inputData.getInt(Constants.op1Key, -1)
        val op2 = parameters.inputData.getInt(Constants.op2Key, -1)
        val jobId = parameters.inputData.getInt(Constants.jobId, -1)

        val parseEquation = util.parseEquation(equation!!,op1, op2)

        val build = Data.Builder()
            .putString("res", parseEquation.toString())
            .putInt(Constants.jobId, jobId).build()

        return Result.success(build)
    }
}