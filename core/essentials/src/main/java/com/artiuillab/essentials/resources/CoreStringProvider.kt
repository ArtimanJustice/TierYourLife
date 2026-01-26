package com.artiuillab.essentials.resources

interface CoreStringProvider : StringProvider {
    val connectionErrorMessage: String
    val unknownErrorMessage: String
    fun backendErrorMessage(code: Int, backendMessage: String): String
}
