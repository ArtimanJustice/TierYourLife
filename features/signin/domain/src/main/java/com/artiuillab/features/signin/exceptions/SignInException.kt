package com.artiuillab.features.signin.exceptions

import com.artiuillab.features.signin.exceptions.base.AbstractSignInAppException
import com.artiuillab.features.signin.resources.SignInStringProvider

class SignInException : AbstractSignInAppException("Specific feature error") {

    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return TODO()
    }

}
