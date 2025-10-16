package com.libraryofthings.data.datasource

import com.libraryofthings.domain.model.Item
import com.libraryofthings.domain.model.ItemStatus
import kotlinx.coroutines.flow.Flow

interface ItemRemoteDataSource {
    suspend fun addItem(item: Item): Result<Unit>
    suspend fun getItemsByOwner(ownerId: String): Flow<List<Item>>
    suspend fun searchAvailableItems(query: String): Result<List<Item>>
    suspend fun borrowItem(itemId: String, borrowerId: String): Result<Unit>
    suspend fun getBorrowedItems(borrowerId: String): Flow<List<Item>>
    suspend fun updateItemStatus(itemId: String, newStatus: ItemStatus): Result<Unit>
    suspend fun finalizeReturn(itemId: String): Result<Unit>
}
