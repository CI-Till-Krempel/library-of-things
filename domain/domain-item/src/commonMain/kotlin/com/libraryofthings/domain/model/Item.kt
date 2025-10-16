package com.libraryofthings.domain.model

data class Item(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val ownerId: String,
    val status: ItemStatus,
    val borrowerId: String? = null
)
