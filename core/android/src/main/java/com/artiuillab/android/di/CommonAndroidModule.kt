package com.artiuillab.android.di

import com.artiuillab.android.logger.AndroidLogger
import com.artiuillab.android.resources.AndroidStringProvider
import com.artiuillab.essentials.logger.Logger
import com.artiuillab.essentials.resources.StringProvider
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

    @Binds
    fun bindStringProvider(
        impl: AndroidStringProvider
    ): StringProvider
}