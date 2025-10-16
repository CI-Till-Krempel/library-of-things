# Implementation Plan: View Borrowed Items

This plan outlines the technical implementation for the "View Borrowed Items" user story.

## 1. Goal

To display a list of items that the currently logged-in user is borrowing from others.

## 2. Affected Layers

*   **UI Layer:** A new screen or a section in the user's profile.
*   **Presentation Layer (ViewModel):** A `MyBorrowedItemsViewModel`.
*   **Domain Layer (UseCase):** A `GetMyBorrowedItemsUseCase`.
*   **Domain & Data Layers:** Uses the existing `ItemRepository`.

## 3. Implementation Details

### UI Layer (`MyBorrowedItemsScreen.kt`)

*   A Composable function `MyBorrowedItemsScreen` will be created.
*   It will use a `LazyColumn` to display the list of borrowed items.
*   Each item card will show the item's name and potentially the lender's name.
*   The screen will observe state from the `MyBorrowedItemsViewModel`.

### Presentation Layer (`MyBorrowedItemsViewModel.kt`)

*   `StateFlow<MyBorrowedItemsState>`: Exposes UI state (`isLoading`, `error`, `items: List<Item>`).
*   `loadBorrowedItems()` function (called on init):
    1.  Calls the `GetMyBorrowedItemsUseCase`.
    2.  Updates the state with the list of items or an error.

### Domain Layer

*   **`usecase/GetMyBorrowedItemsUseCase.kt`**:
    *   Injects `ItemRepository` and `UserRepository`.
    *   `invoke()` method will get the current user's ID and call `itemRepository.getBorrowedItems(borrowerId)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun getBorrowedItems(borrowerId: String): Flow<List<Item>>`. A `Flow` allows the UI to update automatically.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `getBorrowedItems` by delegating to the data sources, similar to `getItemsByOwner`.
*   **`datasource/ItemRemoteDataSource.kt`**: Add `getBorrowedItems(borrowerId)` method.
    *   The Firestore implementation will query the `items` collection for documents where the `borrowerId` field matches the current user's ID.
*   **`datasource/ItemLocalDataSource.kt`**: Add `getBorrowedItems(borrowerId)` method that returns a `Flow` from the local SQLDelight database.

## 4. Testability

*   **ViewModel:** Mock the `GetMyBorrowedItemsUseCase`.
*   **UseCase:** Mock the `ItemRepository` and `UserRepository`.
*   **Repository:** Mock the local and remote data sources.
