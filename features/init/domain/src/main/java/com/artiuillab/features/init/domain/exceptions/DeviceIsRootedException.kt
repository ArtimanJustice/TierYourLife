package com.artiuillab.features.init.domain.exceptions

import com.artiuillab.features.init.domain.resources.InitStringProvider
import com.artiuillab.features.init.domain.exceptions.base.AbstractInitAppException

class DeviceIsRootedException : AbstractInitAppException("Device is rooted") {

    override fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String {
        return stringProvider.deviceIsRootedErrorMessage
    }

}
