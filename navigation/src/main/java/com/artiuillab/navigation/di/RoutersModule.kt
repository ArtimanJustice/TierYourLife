package com.artiuillab.navigation.di

import com.artiuillab.features.presentation.InitRouter
import com.artiuillab.navigation.routers.InitRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RoutersModule {

    @Binds
    fun bindInitRouter(impl: InitRouterImpl): InitRouter

}
