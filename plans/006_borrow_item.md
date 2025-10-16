# Implementation Plan: Borrow Item

This plan outlines the technical implementation for the "Borrow Item" user story.

## 1. Goal

To allow a user to borrow an available item, which updates the item's status and creates a borrowing record.

## 2. Affected Layers

*   **UI Layer:** The item detail screen will have a "Borrow" button.
*   **Presentation Layer (ViewModel):** An `ItemDetailViewModel` will handle the borrow action.
*   **Domain Layer (UseCase):** A `BorrowItemUseCase`.
*   **Domain & Data Layers:** Uses the `ItemRepository` and `UserRepository`.

## 3. Implementation Details

### UI Layer (`ItemDetailScreen.kt`)

*   The existing item detail screen will feature a "Borrow" button.
*   The button's enabled state will be controlled by the ViewModel, based on whether the user is eligible to borrow (i.e., they are lending at least one item).
*   A confirmation dialog will be shown when the user clicks "Borrow".

### Presentation Layer (`ItemDetailViewModel.kt`)

*   `StateFlow<ItemDetailState>`: Exposes UI state (`isLoading`, `error`, `isBorrowSuccessful`, `canBorrow`).
*   The ViewModel will check if the user can borrow by checking if their lending inventory is empty.
*   `borrowItem(itemId: String)` function:
    1.  Sets state to `isLoading = true`.
    2.  Calls the `BorrowItemUseCase`.
    3.  Updates the state based on the result.

### Domain Layer

*   **`usecase/BorrowItemUseCase.kt`**:
    *   Injects `ItemRepository` and `UserRepository`.
    *   `invoke(itemId: String)` method:
        1.  Gets the current user's ID from `UserRepository`.
        2.  Calls `itemRepository.borrowItem(itemId, borrowerId)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun borrowItem(itemId: String, borrowerId: String): Result<Unit>`.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `borrowItem` by calling the remote data source.
*   **`datasource/ItemRemoteDataSource.kt` (Interface & Implementation)**:
    *   Add `borrowItem(itemId, borrowerId)` method.
    *   The Firestore implementation will update the specified item document, setting its `status` to "Lent Out" and adding the `borrowerId`.
    *   This should be performed as an atomic transaction to prevent race conditions where two users try to borrow the same item simultaneously.

## 4. Testability

*   **ViewModel:** Mock the `BorrowItemUseCase`.
*   **UseCase:** Mock the `ItemRepository` and `UserRepository`.
*   **Repository:** Mock the `ItemRemoteDataSource`.
