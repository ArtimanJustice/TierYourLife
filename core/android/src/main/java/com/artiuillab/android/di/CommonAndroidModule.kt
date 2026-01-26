package com.artiuillab.android.di

import com.artiuillab.android.logger.AndroidLogger
import com.artiuillab.android.resources.CoreStringProviderImpl
import com.artiuillab.essentials.exceptions.mapper.DefaultExceptionToMessageMapper
import com.artiuillab.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.artiuillab.essentials.logger.Logger
import com.artiuillab.essentials.resources.CoreStringProvider
import com.artiuillab.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(
        logger: AndroidLogger
    ): Logger

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindCoreStringProvider(
        impl: CoreStringProviderImpl
    ): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(
        impl: DefaultExceptionToMessageMapper
    ): ExceptionToMessageMapper

}
