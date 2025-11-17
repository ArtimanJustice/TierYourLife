package com.sinyee.core_android

import android.app.Application
import com.sinyee.core_android.logger.AndroidLogger
import com.sinyee.core_essentials.logger.Logger
import timber.log.Timber

abstract class AbstractApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Logger.set(AndroidLogger())
    }
}