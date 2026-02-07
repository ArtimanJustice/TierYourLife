package com.artiuillab.features.signin.di

import com.artiuillab.essentials.resources.StringProvider
import com.artiuillab.features.signin.resources.SignInStringProvider
import com.artiuillab.features.signin.resources.SignInStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SignInStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SignInStringProvider::class)
    fun bindSignInStringProvider(
        impl: SignInStringProviderImpl
    ): StringProvider

}
