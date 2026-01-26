package com.artiuillab.essentials.exceptions.base

import com.artiuillab.essentials.resources.StringProviderStore

interface WithLocalizedMessage {
    fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String
}
