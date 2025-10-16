# Implementation Plan: Borrower Initiates Return

This plan outlines the technical implementation for the "Borrower Initiates Return" user story.

## 1. Goal

To allow a borrower to signal that they have returned an item, putting it into a "Pending Return" state.

## 2. Affected Layers

*   **UI Layer:** The "My Borrowed Items" screen will have a button to initiate the return.
*   **Presentation Layer (ViewModel):** The `MyBorrowedItemsViewModel` will handle the action.
*   **Domain Layer (UseCase):** An `InitiateReturnUseCase`.
*   **Domain & Data Layers:** Uses the existing `ItemRepository`.

## 3. Implementation Details

### UI Layer (`MyBorrowedItemsScreen.kt`)

*   Each item card in the borrowed items list will have an "I've Returned This" button.
*   Clicking the button will call the corresponding function in the `MyBorrowedItemsViewModel`.
*   The UI for the item card will change to reflect the "Pending Return" status (e.g., greyed out, status text update).

### Presentation Layer (`MyBorrowedItemsViewModel.kt`)

*   `initiateReturn(itemId: String)` function:
    1.  Sets a temporary loading state for that specific item.
    2.  Calls the `InitiateReturnUseCase`.
    3.  On success, the `Flow` from the repository should automatically update the list, and the UI will recompose to show the new "Pending Return" status.

### Domain Layer

*   **`usecase/InitiateReturnUseCase.kt`**:
    *   Injects `ItemRepository`.
    *   `invoke(itemId: String)` method will call `itemRepository.updateItemStatus(itemId, ItemStatus.PENDING_RETURN)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun updateItemStatus(itemId: String, newStatus: ItemStatus): Result<Unit>`.
*   **`model/ItemStatus.kt`**: An enum will be created for item statuses (`Available`, `LentOut`, `PendingReturn`, `Unavailable`).

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `updateItemStatus` by calling the remote data source.
*   **`datasource/ItemRemoteDataSource.kt` (Interface & Implementation)**:
    *   Add `updateItemStatus(itemId, newStatus)` method.
    *   The Firestore implementation will update the `status` field of the specified item document.

## 4. Testability

*   **ViewModel:** Mock the `InitiateReturnUseCase`.
*   **UseCase:** Mock the `ItemRepository`.
*   **Repository:** Mock the `ItemRemoteDataSource`.
