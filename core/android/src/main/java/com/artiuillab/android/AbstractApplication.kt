package com.artiuillab.android

import android.app.Application
import com.artiuillab.android.logger.AndroidLogger
import com.artiuillab.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.artiuillab.essentials.logger.Logger
import timber.log.Timber
import javax.inject.Inject
import kotlin.text.set

abstract class AbstractApplication : Application() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Logger.set(logger)
        ExceptionToMessageMapper.set(exceptionToMessageMapper)
    }

}
