# Technical Task: Firebase Auth Integration

## 1. Goal

To establish a working integration with Firebase Authentication within the `shared` module, enabling user registration and login functionalities.

## 2. Affected Components

*   **`shared/src/commonMain/kotlin/com/libraryofthings/data/datasource/UserRemoteDataSource.kt`**: Interface for user authentication operations.
*   **`shared/src/commonMain/kotlin/com/libraryofthings/data/remote/FirebaseRealtimeDbDataSource.kt`**: Implementation of `UserRemoteDataSource` using `gitlive-firebase-auth`.
*   **`shared/build.gradle.kts`**: Add `gitlive-firebase-auth` dependency.
*   **Firebase Project:** Ensure Firebase Authentication is enabled in the Firebase console (Email/Password provider).

## 3. Steps to Implementation

1.  **Add Dependency:** Add `implementation(libs.firebase.auth)` to `shared/build.gradle.kts` `commonMain` dependencies.
2.  **Implement `register` in `FirebaseRealtimeDbDataSource`:**
    *   Use `Firebase.auth.createUserWithEmailAndPassword(email, password)` to register a user.
    *   Map the resulting `FirebaseUser` to the domain `User` model.
    *   Handle potential exceptions (e.g., `FirebaseAuthUserCollisionException`).
3.  **Implement `login` in `FirebaseRealtimeDbDataSource`:**
    *   Use `Firebase.auth.signInWithEmailAndPassword(email, password)` to log in a user.
    *   Map the resulting `FirebaseUser` to the domain `User` model.
    *   Handle potential exceptions (e.g., `FirebaseAuthInvalidCredentialsException`).
4.  **Implement `getCurrentUser` (optional but recommended):** Add a method to `UserRemoteDataSource` and its Firebase implementation to get the currently logged-in user.
5.  **Test:** Write unit tests for the `FirebaseRealtimeDbDataSource` (mocking Firebase Auth) and integration tests if possible.

## 4. Verification

*   User registration via the `register` method successfully creates a user in Firebase Auth.
*   User login via the `login` method successfully authenticates a user.
*   Error cases (e.g., invalid credentials, email already in use) are handled gracefully.
