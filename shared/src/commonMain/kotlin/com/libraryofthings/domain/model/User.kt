package com.libraryofthings.domain.model

data class User(
    val id: String? = null,
    val email: String,
    val displayName: String? = null
)
