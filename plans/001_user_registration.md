# Implementation Plan: User Registration

This plan outlines the technical implementation for the "User Registration" user story.

## 1. Goal

To allow a new user to create an account using their email and password via Firebase Authentication.

## 2. Affected Layers

*   **UI Layer:** A new registration screen will be created.
*   **Presentation Layer (ViewModel):** A `RegistrationViewModel` will manage the screen's state and handle user input.
*   **Domain Layer (UseCase):** A `RegisterUserUseCase` will be created to encapsulate the registration logic.
*   **Data Layer (Repository):** The `UserRepository` will be extended to include a `register` method.
*   **Data Layer (DataSource):** A `UserRemoteDataSource` will interact directly with the Firebase Auth API.

## 3. Implementation Details

### UI Layer (`RegistrationScreen.kt`)

*   A Composable function `RegistrationScreen` will be created.
*   It will contain `TextField`s for email, password, and password confirmation.
*   A `Button` will trigger the registration process.
*   It will observe the state (`RegistrationScreenState`) from the `RegistrationViewModel` to show loading indicators or error messages.

### Presentation Layer (`RegistrationViewModel.kt`)

*   `StateFlow<RegistrationScreenState>`: Exposes the UI state (e.g., `isLoading`, `error`, `isRegistrationSuccessful`).
*   `registerUser(email, password)` function:
    1.  Performs basic validation (e.g., non-empty fields).
    2.  Sets state to `isLoading = true`.
    3.  Calls the `RegisterUserUseCase`.
    4.  Updates the state based on the `Result` from the use case (success or failure).

### Domain Layer

*   **`RegisterUserUseCase.kt`**:
    *   Injects the `UserRepository`.
    *   `invoke(email, password)` method will call `userRepository.register(email, password)`.
*   **`UserRepository.kt` (Interface)**:
    *   Add new method: `suspend fun register(email: String, password: String): Result<User>`.

### Data Layer

*   **`UserRepositoryImpl.kt`**:
    *   Implements the `register` method by calling the `FirebaseRealtimeDbDataSource`.
    *   `override suspend fun register(...) = firebaseRealtimeDbDataSource.register(...)`
*   **`UserRemoteDataSource.kt` (Interface)**:
    *   Add new method: `suspend fun register(email: String, password: String): Result<User>`.
*   **`FirebaseRealtimeDbDataSource.kt` (Implementation)**:
    *   Inject the Firebase Auth instance (`FirebaseAuth`).
    *   Implement the `register` method:
        1.  Call `Firebase.auth.createUserWithEmailAndPassword(email, password)`.
        2.  On success, map the Firebase `FirebaseUser` to our domain `User` model and return `Result.success(user)`.
        3.  On failure, catch exceptions (e.g., `FirebaseAuthUserCollisionException`) and return `Result.failure(exception)`.

## 4. Testability

*   **ViewModel:** Can be tested by mocking the `RegisterUserUseCase`.
*   **UseCase:** Can be tested by mocking the `UserRepository`.
*   **Repository:** Can be tested by mocking the `UserRemoteDataSource`.
