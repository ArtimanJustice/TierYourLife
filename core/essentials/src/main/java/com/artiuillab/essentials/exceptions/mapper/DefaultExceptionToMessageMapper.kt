package com.artiuillab.essentials.exceptions.mapper

import com.artiuillab.essentials.exceptions.base.WithLocalizedMessage
import com.artiuillab.essentials.resources.CoreStringProvider
import com.artiuillab.essentials.resources.StringProviderStore
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore,
) : ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            stringProviderStore<CoreStringProvider>().unknownErrorMessage
        }
    }
}
