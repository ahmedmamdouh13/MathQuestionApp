package com.ahmedmamdouh13.ama.mathquestionapp.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ahmedmamdouh13.ama.mathquestionapp.util.MathUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class MathWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val util: MathUtil
) : Worker(context, workerParameters) {


    override fun doWork(): Result {

        util.printToo("success")

        return Result.success()
    }
}