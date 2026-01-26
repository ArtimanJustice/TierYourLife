package com.artiuillab.essentials.exceptions

import com.artiuillab.essentials.exceptions.base.AbstractCoreAppException
import com.artiuillab.essentials.resources.CoreStringProvider

class BackendException(
    private val code: Int = 400,
    private val backendMessage: String = "",
    cause: Throwable? = null,
) : AbstractCoreAppException("Server error", cause) {

    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.backendErrorMessage(code, backendMessage)
    }

}
