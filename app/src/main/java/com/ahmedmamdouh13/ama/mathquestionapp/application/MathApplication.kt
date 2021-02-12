package com.ahmedmamdouh13.ama.mathquestionapp.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import cn.hikyson.godeye.core.BuildConfig
import cn.hikyson.godeye.core.GodEye
import cn.hikyson.godeye.core.GodEyeConfig
import cn.hikyson.godeye.core.GodEyeHelper
import cn.hikyson.godeye.core.exceptions.UninstallException
import cn.hikyson.godeye.core.internal.modules.methodcanary.MethodCanaryConfig
import cn.hikyson.godeye.core.internal.modules.methodcanary.MethodsRecordInfo
import cn.hikyson.godeye.core.monitor.AppInfoLabel
import dagger.hilt.android.HiltAndroidApp
import java.lang.String
import javax.inject.Inject


@HiltAndroidApp
class MathApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override fun onCreate() {
        super.onCreate()



    }

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()


}