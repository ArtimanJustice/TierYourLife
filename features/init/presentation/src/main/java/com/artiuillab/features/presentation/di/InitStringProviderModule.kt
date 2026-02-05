package com.artiuillab.features.presentation.di

import com.artiuillab.essentials.resources.StringProvider
import com.artiuillab.features.init.domain.resources.InitStringProvider
import com.artiuillab.features.presentation.resources.InitStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface InitStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(InitStringProvider::class)
    fun bindInitStringProvider(
        impl: InitStringProviderImpl
    ): StringProvider

}
