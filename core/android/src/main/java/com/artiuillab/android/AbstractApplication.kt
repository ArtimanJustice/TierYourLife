package com.artiuillab.android

import android.app.Application
import com.artiuillab.android.logger.AndroidLogger
import com.artiuillab.essentials.logger.Logger
import timber.log.Timber

abstract class AbstractApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Logger.set(AndroidLogger())
    }
}