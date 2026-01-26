package com.artiuillab.features.presentation.resources

import android.content.Context
import com.artiuillab.features.init.domain.resources.InitStringProvider
import com.artiuillab.features.init.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class InitStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
): InitStringProvider {

    override val deviceIsRootedErrorMessage: String = context.getString(R.string.rooted_device_error)

}
