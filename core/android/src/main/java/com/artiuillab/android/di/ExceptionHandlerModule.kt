package com.artiuillab.android.di

import com.artiuillab.android.AndroidExceptionHandler
import com.artiuillab.essentials.exceptions.handler.ExceptionHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ExceptionHandlerModule {

    @Binds
    fun bindExceptionHandler(
        impl: AndroidExceptionHandler
    ): ExceptionHandler

}
