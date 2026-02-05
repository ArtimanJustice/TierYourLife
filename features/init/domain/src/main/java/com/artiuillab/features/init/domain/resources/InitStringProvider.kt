package com.artiuillab.features.init.domain.resources

import com.artiuillab.essentials.resources.StringProvider

interface InitStringProvider : StringProvider {
    val deviceIsRootedErrorMessage: String
}
