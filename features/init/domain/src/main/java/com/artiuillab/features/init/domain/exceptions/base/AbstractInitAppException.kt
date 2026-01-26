package com.artiuillab.features.init.domain.exceptions.base

import com.artiuillab.essentials.exceptions.base.AbstractAppException
import com.artiuillab.essentials.exceptions.base.WithLocalizedMessage
import com.artiuillab.essentials.resources.StringProviderStore
import com.artiuillab.features.init.domain.resources.InitStringProvider

abstract class AbstractInitAppException(
    message: String,
    cause: Throwable? = null,
) : AbstractAppException(message, cause), WithLocalizedMessage {

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String {
        return getLocalizedErrorMessage(stringProviderStore<InitStringProvider>())
    }

    abstract fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String
}
