package com.artiuillab.features.signin.exceptions.base

import com.artiuillab.essentials.exceptions.base.AbstractAppException
import com.artiuillab.essentials.exceptions.base.WithLocalizedMessage
import com.artiuillab.essentials.resources.StringProviderStore
import com.artiuillab.features.signin.resources.SignInStringProvider

abstract class AbstractSignInAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<SignInStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String
}
