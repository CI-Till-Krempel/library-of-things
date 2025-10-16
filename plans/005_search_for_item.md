# Implementation Plan: Search for Item

This plan outlines the technical implementation for the "Search for Item" user story.

## 1. Goal

To allow a user to search for available items by name from the community's master catalog.

## 2. Affected Layers

*   **UI Layer:** A search screen with a search bar and a list of results.
*   **Presentation Layer (ViewModel):** A `SearchViewModel` to handle search queries and result state.
*   **Domain Layer (UseCase):** A `SearchAvailableItemsUseCase`.
*   **Domain & Data Layers:** Uses the existing `ItemRepository`.

## 3. Implementation Details

### UI Layer (`SearchScreen.kt`)

*   A Composable function `SearchScreen` will be created.
*   It will contain a `TextField` for the search query.
*   A `LazyColumn` will display the search results.
*   The screen will observe the `SearchState` from the `SearchViewModel`.

### Presentation Layer (`SearchViewModel.kt`)

*   `StateFlow<SearchState>`: Exposes UI state (`isLoading`, `error`, `results: List<Item>`).
*   `onSearchQueryChanged(query: String)` function:
    1.  Updates the search query in the state.
    2.  Debounces the input to avoid excessive searches while the user is typing.
    3.  Calls `SearchAvailableItemsUseCase` with the new query.
    4.  Updates the state with the results.

### Domain Layer

*   **`usecase/SearchAvailableItemsUseCase.kt`**:
    *   Injects `ItemRepository` and `UserRepository` (to get current user's ID to exclude their own items).
    *   `invoke(query: String)` method will call `itemRepository.searchAvailableItems(query, currentUserId)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun searchAvailableItems(query: String, currentUserId: String): Result<List<Item>>`.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `searchAvailableItems`.
    *   It will call the `FirebaseRealtimeDbDataSource` to perform the search on the backend.
    *   It will filter out any items where the `ownerId` matches the `currentUserId`.
*   **`datasource/ItemRemoteDataSource.kt` (Interface)**:
    *   The interface for remote item operations.
*   **`FirebaseRealtimeDbDataSource.kt` (Implementation)**:
    *   The `searchAvailableItems` method will use `Firebase.database.reference("items").orderByChild("name").startAt(query).endAt(query + "\uf8ff")` to perform a prefix search on the Realtime Database. It will also filter by `status == Available` and `ownerId != currentUserId`.
*   **Note:** Realtime Database has limitations for complex text searches. For the MVP, a prefix search will be implemented. More advanced search might require a dedicated search service in the future.

## 4. Testability

*   **ViewModel:** Mock the `SearchAvailableItemsUseCase`.
*   **UseCase:** Mock the `ItemRepository` and `UserRepository`.
*   **Repository:** Mock the `ItemRemoteDataSource`.
