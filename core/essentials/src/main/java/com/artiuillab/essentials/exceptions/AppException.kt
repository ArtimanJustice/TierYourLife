package com.artiuillab.essentials.exceptions

abstract class AppException(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause)

class UnknownException : AppException("Unknown exception")
