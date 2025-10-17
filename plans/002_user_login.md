# Implementation Plan: User Login

This plan outlines the technical implementation for the "User Login" user story.

## 1. Goal

To allow a registered user to sign in using their email and password via Firebase Authentication.

## 2. Affected Layers

*   **UI Layer:** A new login screen will be created.
*   **Presentation Layer (ViewModel):** A `LoginViewModel` will manage the screen's state.
*   **Domain Layer (UseCase):** A `LoginUserUseCase` will be created.
*   **Data Layer (Repository & DataSource):** The existing `UserRepository` and `UserRemoteDataSource` will be used.

## 3. Implementation Details

### UI Layer (`LoginScreen.kt`)

*   A Composable function `LoginScreen` will be created.
*   It will contain `TextField`s for email and password, and a `Button` to trigger login.
*   It will observe the state from the `LoginViewModel` to show loading indicators or error messages.

### Presentation Layer (`LoginViewModel.kt`)

*   `StateFlow<LoginScreenState>`: Exposes the UI state (`isLoading`, `error`, `isLoginSuccessful`).
*   `loginUser(email, password)` function:
    1.  Sets state to `isLoading = true`.
    2.  Calls the `LoginUserUseCase`.
    3.  Updates the state based on the `Result` from the use case.

### Domain Layer

*   **`LoginUserUseCase.kt`**:
    *   Injects the `UserRepository`.
    *   `invoke(email, password)` method will call `userRepository.login(email, password)`.
*   **`UserRepository.kt` (Interface)**:
    *   Add new method: `suspend fun login(email: String, password: String): Result<User>`.

### Data Layer

*   **`UserRepositoryImpl.kt`**:
    *   Implements the `login` method by calling the `FirebaseRealtimeDbDataSource`.
    *   `override suspend fun login(...) = firebaseRealtimeDbDataSource.login(...)`
*   **`UserRemoteDataSource.kt` (Interface)**:
    *   Add new method: `suspend fun login(email: String, password: String): Result<User>`.
*   **`FirebaseRealtimeDbDataSource.kt` (Implementation)**:
    *   Implement the `login` method:
        1.  Call `Firebase.auth.signInWithEmailAndPassword(email, password)`.
        2.  On success, map the `FirebaseUser` to our domain `User` and return `Result.success(user)`.
        3.  On failure, catch exceptions and return `Result.failure(exception)`.

## 4. Session Persistence

A separate use case, `CheckSessionUseCase`, will be created to check if a user is already logged in when the app starts. The `MainActivity` (Android) or `Main.kt` (Desktop) will call this use case on startup to decide whether to navigate to the main app screen or the login/registration screen.

## 5. Testability

*   **ViewModel:** Can be tested by mocking the `LoginUserUseCase`.
*   **UseCase:** Can be tested by mocking the `UserRepository`.
