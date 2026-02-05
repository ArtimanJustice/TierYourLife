package com.artiuillab.features.init.domain

import com.artiuillab.essentials.exceptions.ConnectionException
import kotlinx.coroutines.delay
import javax.inject.Inject

// todo: implement auth check; use interfaces
class IsAuthorizedUseCase @Inject constructor() {

    suspend fun invoke(): Boolean {
        delay(2000)
        throw ConnectionException()
    }

}