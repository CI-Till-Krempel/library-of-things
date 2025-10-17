# Technical Task: Firebase Realtime Database Setup

## 1. Goal

To establish a working connection and basic CRUD operations with Firebase Realtime Database for item and user data.

## 2. Affected Components

*   **`shared/src/commonMain/kotlin/com/libraryofthings/data/datasource/ItemRemoteDataSource.kt`**: Interface for item data operations.
*   **`shared/src/commonMain/kotlin/com/libraryofthings/data/remote/FirebaseRealtimeDbDataSource.kt`**: Implementation of `ItemRemoteDataSource` using `gitlive-firebase-database`.
*   **`shared/build.gradle.kts`**: Ensure `gitlive-firebase-database` dependency is present.
*   **Firebase Project:** Ensure Firebase Realtime Database is enabled and security rules are configured for basic read/write access.

## 3. Steps to Implementation

1.  **Verify Dependency:** Ensure `implementation(libs.firebase.database)` is in `shared/build.gradle.kts` `commonMain` dependencies.
2.  **Configure Realtime Database Rules:** Set up basic security rules in the Firebase console to allow authenticated users to read and write their own data (e.g., items they own, items they borrow).
3.  **Implement `addItem` in `FirebaseRealtimeDbDataSource`:**
    *   Use `Firebase.database.reference("items").child(item.id).setValue(item)` to save an item.
4.  **Implement `getItemsByOwner` in `FirebaseRealtimeDbDataSource`:**
    *   Use `Firebase.database.reference("items").orderByChild("ownerId").equalTo(ownerId)` to query items.
    *   Convert `DataSnapshot` to `Flow<List<Item>>`.
5.  **Implement `searchAvailableItems` in `FirebaseRealtimeDbDataSource`:**
    *   Use `Firebase.database.reference("items").orderByChild("name").startAt(query).endAt(query + "\uf8ff")` for prefix search.
    *   Filter by `status == Available` and `ownerId != currentUserId`.
6.  **Implement `borrowItem` in `FirebaseRealtimeDbDataSource`:**
    *   Use a transaction (`runTransaction`) to update item status and `borrowerId` atomically.
7.  **Implement `getBorrowedItems` in `FirebaseRealtimeDbDataSource`:**
    *   Query `Firebase.database.reference("items").orderByChild("borrowerId").equalTo(borrowerId)`.
8.  **Implement `updateItemStatus` in `FirebaseRealtimeDbDataSource`:**
    *   Update the `status` field of an item.
9.  **Implement `finalizeReturn` in `FirebaseRealtimeDbDataSource`:**
    *   Update `status` to `Available` and remove `borrowerId`.
10. **Test:** Write unit tests for the `FirebaseRealtimeDbDataSource` (mocking Firebase Realtime Database) and integration tests if possible.

## 4. Verification

*   Items can be added, retrieved, and updated in the Realtime Database.
*   Security rules correctly enforce access control.
*   All item-related operations (add, view, search, borrow, return) function correctly against the Realtime Database.
