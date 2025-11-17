package com.sinyee.core_android.di

import com.sinyee.core_android.logger.AndroidLogger
import com.sinyee.core_essentials.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(
        logger: AndroidLogger
    ): Logger
}