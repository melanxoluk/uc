package dev.melanxoluk.uc

class UcException(
    override val message: String, 
    override val cause: Throwable? = null
) : Exception(message, cause)
