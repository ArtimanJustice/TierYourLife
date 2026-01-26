package com.artiuillab.essentials.exceptions.mapper

import com.artiuillab.essentials.exceptions.mapper.ExceptionToMessageMapper

class EmptyExceptionToMessageMapper : ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: Exception): String {
        return exception.message ?: "Unknown error occurred"
    }

}
