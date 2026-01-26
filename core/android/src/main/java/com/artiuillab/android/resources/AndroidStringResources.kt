package com.artiuillab.android.resources

import android.content.Context
import com.artiuillab.android.R
import com.artiuillab.essentials.resources.StringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidStringProvider @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : StringProvider {
    override val exampleMessage: String
        get() = context.getString(R.string.example_message)
}
