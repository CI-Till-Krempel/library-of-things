# Implementation Plan: Lender Confirms Return

This plan outlines the technical implementation for the "Lender Confirms Return" user story.

## 1. Goal

To allow a lender to confirm they have received their item back, making it available again for borrowing.

## 2. Affected Layers

*   **UI Layer:** The "My Lending Items" screen will show an action button for items pending return.
*   **Presentation Layer (ViewModel):** The `MyLendingItemsViewModel` will handle the confirmation action.
*   **Domain Layer (UseCase):** A `ConfirmReturnUseCase`.
*   **Domain & Data Layers:** Uses the existing `ItemRepository`.

## 3. Implementation Details

### UI Layer (`MyLendingItemsScreen.kt`)

*   For any item with a "Pending Return" status, a "Confirm Return" button will be displayed on its card.
*   Clicking the button will call the corresponding function in the `MyLendingItemsViewModel`.

### Presentation Layer (`MyLendingItemsViewModel.kt`)

*   `confirmReturn(itemId: String)` function:
    1.  Sets a temporary loading state for that item.
    2.  Calls the `ConfirmReturnUseCase`.
    3.  On success, the repository's `Flow` will automatically emit the updated list, and the UI will update.

### Domain Layer

*   **`usecase/ConfirmReturnUseCase.kt`**:
    *   Injects `ItemRepository`.
    *   `invoke(itemId: String)` method will call `itemRepository.finalizeReturn(itemId)`.
*   **`repository/ItemRepository.kt` (Interface)**:
    *   Add method: `suspend fun finalizeReturn(itemId: String): Result<Unit>`.

### Data Layer

*   **`repository/ItemRepositoryImpl.kt`**:
    *   Implements `finalizeReturn` by calling the remote data source.
*   **`datasource/ItemRemoteDataSource.kt` (Interface & Implementation)**:
    *   Add `finalizeReturn(itemId)` method.
    *   The Firestore implementation will perform an atomic update on the item document, setting the `status` to "Available" and removing the `borrowerId` field.

## 4. Testability

*   **ViewModel:** Mock the `ConfirmReturnUseCase`.
*   **UseCase:** Mock the `ItemRepository`.
*   **Repository:** Mock the `ItemRemoteDataSource`.
