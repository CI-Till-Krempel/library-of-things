# Implementation Plan: Add Lending Item

This plan outlines the technical implementation for the "Add Lending Item" user story.

## 1. Goal

To allow a logged-in user to add a new item to their personal inventory, which will be stored in a database.

## 2. Affected Layers

*   **UI Layer:** A new screen for adding/editing an item.
*   **Presentation Layer (ViewModel):** An `AddItemViewModel` to manage the state of the add/edit form.
*   **Domain Layer (UseCase):** An `AddLendingItemUseCase`.
*   **Domain Layer (Repository):** A new `ItemRepository` will be created.
*   **Data Layer (DataSource):** A new `ItemLocalDataSource` and `ItemRemoteDataSource` will be created to handle persistence.

## 3. Implementation Details

### UI Layer (`AddItemScreen.kt`)

*   A Composable function `AddItemScreen` will be created.
*   It will contain `TextField`s for the item's Name, Description, and Category.
*   A `Button` will trigger the save action.
*   The screen will observe state from the `AddItemViewModel`.

### Presentation Layer (`AddItemViewModel.kt`)

*   `StateFlow<AddItemState>`: Exposes UI state (`isLoading`, `error`, `isSaveSuccessful`).
*   `saveItem(name, description, category)` function:
    1.  Validates input.
    2.  Calls the `AddLendingItemUseCase`.
    3.  Updates state based on the result.

### Domain Layer

*   **`model/Item.kt`**: A data class for the `Item` entity will be created (id, name, description, category, ownerId, status).
*   **`usecase/AddLendingItemUseCase.kt`**:
    *   Injects `ItemRepository`.
    *   `invoke(item)` method will call `itemRepository.addItem(item)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   A new repository for item-related operations.
    *   Add method: `suspend fun addItem(item: Item): Result<Unit>`.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements the `ItemRepository` interface.
    *   The `addItem` method will first try to save the item to the `FirebaseRealtimeDbDataSource`. On success, it will also save it to the local data source (cache).
*   **`datasource/ItemRemoteDataSource.kt` (Interface)**:
    *   The interface for remote item operations.
*   **`FirebaseRealtimeDbDataSource.kt` (Implementation)**:
    *   The `addItem` method will use `Firebase.database.reference("items").child(item.id).setValue(item)` to save the item to the Realtime Database.
*   **`datasource/ItemLocalDataSource.kt` (Interface & Implementation)**:
    *   The implementation will use SQLDelight to insert the new item data into a local `items` table.

## 4. Testability

*   **ViewModel:** Mock the `AddLendingItemUseCase`.
*   **UseCase:** Mock the `ItemRepository`.
*   **Repository:** Mock the `ItemLocalDataSource` and `ItemRemoteDataSource`.
