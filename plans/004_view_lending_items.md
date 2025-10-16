# Implementation Plan: View Lending Items

This plan outlines the technical implementation for the "View Lending Items" user story.

## 1. Goal

To display a list of items that the currently logged-in user has made available for lending.

## 2. Affected Layers

*   **UI Layer:** A new screen or section within the user's profile to display the list.
*   **Presentation Layer (ViewModel):** A `MyLendingItemsViewModel` to fetch and manage the list state.
*   **Domain Layer (UseCase):** A `GetMyLendingItemsUseCase`.
*   **Domain & Data Layers:** Uses the existing `ItemRepository`.

## 3. Implementation Details

### UI Layer (`MyLendingItemsScreen.kt`)

*   A Composable function `MyLendingItemsScreen` will be created.
*   It will use a `LazyColumn` to display the list of items.
*   Each item in the list will be a custom `ItemCard` Composable, showing the item's name and status.
*   The screen will observe the `MyLendingItemsState` from the ViewModel.

### Presentation Layer (`MyLendingItemsViewModel.kt`)

*   `StateFlow<MyLendingItemsState>`: Exposes UI state (`isLoading`, `error`, `items: List<Item>`).
*   `loadItems()` function (called on init):
    1.  Sets state to `isLoading = true`.
    2.  Calls the `GetMyLendingItemsUseCase`.
    3.  Updates the state with the list of items or an error.

### Domain Layer

*   **`usecase/GetMyLendingItemsUseCase.kt`**:
    *   Injects `ItemRepository` and `UserRepository` (to get the current user's ID).
    *   `invoke()` method will get the current user's ID, then call `itemRepository.getItemsByOwner(ownerId)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun getItemsByOwner(ownerId: String): Flow<List<Item>>`. Using a `Flow` will allow the UI to update automatically if the underlying data changes.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `getItemsByOwner`.
    *   It will first attempt to fetch the fresh list from the `ItemRemoteDataSource`.
    *   It will then update the local cache (`ItemLocalDataSource`).
    *   It will return a `Flow` that emits the data from the local data source, ensuring the UI is always up-to-date with the cache and reacts to its changes.
*   **`datasource/ItemRemoteDataSource.kt`**: Add `getItemsByOwner(ownerId)` method.
*   **`datasource/ItemLocalDataSource.kt`**: Add `getItemsByOwner(ownerId)` method that returns a `Flow`.

## 4. Testability

*   **ViewModel:** Mock the `GetMyLendingItemsUseCase`.
*   **UseCase:** Mock the `ItemRepository` and `UserRepository`.
*   **Repository:** Mock the local and remote data sources.
