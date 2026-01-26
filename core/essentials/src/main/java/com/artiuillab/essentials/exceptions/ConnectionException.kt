package com.artiuillab.essentials.exceptions

import com.artiuillab.essentials.exceptions.base.AbstractCoreAppException
import com.artiuillab.essentials.resources.CoreStringProvider

class ConnectionException(
    cause: Throwable? = null,
) : AbstractCoreAppException("Network connection error", cause) {

    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String {
        return stringProvider.connectionErrorMessage
    }

}
