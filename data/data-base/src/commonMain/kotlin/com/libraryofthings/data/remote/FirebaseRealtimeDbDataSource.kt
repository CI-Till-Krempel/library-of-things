package com.libraryofthings.data.remote

import com.libraryofthings.data.datasource.ItemRemoteDataSource
import com.libraryofthings.data.datasource.UserRemoteDataSource
import com.libraryofthings.domain.model.Item
import com.libraryofthings.domain.model.ItemStatus
import com.libraryofthings.domain.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow

class FirebaseRealtimeDbDataSource : UserRemoteDataSource, ItemRemoteDataSource {

    private val db = Firebase.database

    override suspend fun register(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun addItem(item: Item): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemsByOwner(ownerId: String): Flow<List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchAvailableItems(query: String): Result<List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun borrowItem(itemId: String, borrowerId: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getBorrowedItems(borrowerId: String): Flow<List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItemStatus(itemId: String, newStatus: ItemStatus): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun finalizeReturn(itemId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
