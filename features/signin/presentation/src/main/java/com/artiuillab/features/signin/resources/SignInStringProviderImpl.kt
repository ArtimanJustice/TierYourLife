package com.artiuillab.features.signin.resources

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): SignInStringProvider {

}
