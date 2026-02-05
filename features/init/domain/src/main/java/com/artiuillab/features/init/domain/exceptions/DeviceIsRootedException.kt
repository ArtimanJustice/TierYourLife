package com.artiuillab.features.init.domain.exceptions

import com.artiuillab.features.init.domain.exceptions.base.AbstractInitAppException
import com.artiuillab.features.init.domain.resources.InitStringProvider

class DeviceIsRootedException : AbstractInitAppException("Device is rooted") {

    override fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String {
        return stringProvider.deviceIsRootedErrorMessage
    }

}
