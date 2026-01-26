package com.artiuillab.essentials.exceptions.base

import com.artiuillab.essentials.resources.CoreStringProvider
import com.artiuillab.essentials.resources.StringProviderStore

abstract class AbstractCoreAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String

}
