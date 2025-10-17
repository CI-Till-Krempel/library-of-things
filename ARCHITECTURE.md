# Architecture Plan: Library of Things

This document outlines the architectural decisions for the Library of Things application, built with Kotlin Multiplatform.

## 1. Guiding Principles

*   **Clean Architecture:** We will enforce a strict separation of concerns between the layers of the application to promote independence, testability, and maintainability.
*   **Shared Logic:** Maximize the amount of shared code across all platforms in the `shared` module.
*   **UI Agnostic Business Logic:** The core business rules (Domain) and application logic (Presentation) will be completely independent of the UI framework.
*   **Testability:** Every layer will be designed to be easily unit-tested.

## 2. Modularization Strategy

The project will be divided into the following modules:

```
/ (root)
|-- androidApp/      # Android-specific application module
|-- desktopApp/      # JVM Desktop-specific application module
|-- iosApp/          # iOS-specific application module (Xcode project)
|-- webApp/          # Web-specific application module (future target)
+-- shared/          # The core KMP module with all shared code
```

*   **`shared` Module:** This is the heart of the application. It contains all the shared business logic, data handling, and UI components. It is structured into source sets:
    *   `commonMain`: Code shared across all platforms.
    *   `androidMain`: Android-specific implementations (e.g., database driver, platform services).
    *   `desktopMain`: Desktop-specific implementations.
    *   `iosMain`: iOS-specific implementations.
    *   `commonTest`: Shared tests for the common code.

*   **Platform-Specific Modules (`androidApp`, `iosApp`, etc.):** These are very thin layers responsible for initializing the shared `ViewModel`s and rendering the shared Compose UI.

## 3. Layered Architecture (within `shared` module)

We will adopt a classic Clean Architecture approach with three main layers: Domain, Data, and Presentation.

![Layer Diagram](https://i.imgur.com/v9zHnQ8.png) 
*Dependency Rule: Arrows point from an outer layer to an inner layer. An inner layer cannot know anything about an outer layer.*

### 3.1. Domain Layer (`shared/src/commonMain/kotlin/com/libraryofthings/domain`)

*   **Purpose:** The core of the application. Contains the business objects and rules. It is pure Kotlin and has no dependencies on any frameworks or libraries outside of the Kotlin standard library.
*   **Contents:**
    *   **`model/`**: Core data classes representing business entities (e.g., `Item`, `User`, `LendingStatus`). These are simple, immutable data holders.
    *   **`repository/`**: Interfaces that define the contracts for data operations (e.g., `ItemRepository`, `UserRepository`). The domain layer does not care how these are implemented.
    *   **`usecase/`**: Classes that encapsulate a single, discrete business operation. They orchestrate the flow of data by using one or more repository interfaces. Examples: `BorrowItemUseCase`, `GetUserLendingItemsUseCase`.
*   **Testability:** Use cases can be unit-tested easily by providing mock implementations of the repository interfaces.

### 3.2. Data Layer (`shared/src/commonMain/kotlin/com/libraryofthings/data`)

*   **Purpose:** Implements the repository interfaces from the Domain layer. It contains the logic for fetching data from various sources (network, local database) and mapping it to the domain models.
*   **Contents:**
    **`repository/`**: Concrete implementations of the repository interfaces (e.g., `ItemRepositoryImpl`). This class will decide whether to fetch data from the remote Firebase database or a local SQLDelight cache.
    *   **`datasource/`**: Interfaces for data sources (e.g., `ItemLocalDataSource`, `ItemRemoteDataSource`).
    *   **`local/` (Persistence):** Implementation of the local data source. We will use **SQLDelight** for type-safe, multiplatform database access.
    *   **`remote/` (Firebase Backend):** Implementation of the remote data source. We will use **Firebase Realtime Database** as our primary backend, accessed via the **Gitlive Firebase** KMP library. A `FirebaseRealtimeDbDataSource` will implement the remote data source interfaces.
*   **Testability:** Repository implementations can be tested by mocking the data sources they depend on.

### 3.3. Presentation Layer (`shared/src/commonMain/kotlin/com/libraryofthings/presentation`)

*   **Purpose:** To manage the state of the UI and react to user interactions. It connects the UI to the business logic.
*   **Contents:**
    *   **`viewmodel/`**: ViewModels will be responsible for holding and managing the UI state. They will be plain classes that survive configuration changes. They will execute UseCases from the Domain layer and expose the UI state as a `StateFlow`.
    *   **`state/`**: Immutable data classes representing the complete state of a screen (e.g., `ItemListScreenState`).
*   **Testability:** ViewModels are tested by mocking the UseCases they call and asserting changes to the UI state.

## 4. UI Layer (`shared/src/commonMain/kotlin/com/libraryofthings/ui`)

*   **Framework:** **Compose Multiplatform** will be used to write the entire UI once in the `shared` module's `commonMain` source set.
*   **Structure:**
    *   **`screens/`**: Composable functions representing full screens (e.g., `HomeScreen`, `ItemDetailScreen`).
    *   **`components/`**: Smaller, reusable UI components (e.g., `ItemCard`, `SearchBar`).
    *   **`theme/`**: Theming and styling (colors, typography, shapes).
*   **State Handling:** The UI will observe the `StateFlow` from the ViewModel and re-render declaratively when the state changes.

## 5. Dependency Injection

We will use a multiplatform-compatible dependency injection library like **Koin** to manage the creation and provision of dependencies (e.g., providing `ItemRepositoryImpl` to `BorrowItemUseCase`, and `BorrowItemUseCase` to a `ViewModel`). This is crucial for decoupling layers and enabling easy testing with mock objects.

## 6. Extensible API Integration for Sharing Providers

To easily integrate with different item-sharing providers in the future, we will use a provider-based, plug-and-play architecture.

1.  **Common Provider Interface:** Define a generic interface in the Data layer:
    ```kotlin
    interface ExternalSharingProvider {
        suspend fun search(query: String): List<Item>
        // Other common operations...
    }
    ```
2.  **Concrete Implementations:** For each external API (e.g., a local library's API, another sharing app), we will create a concrete implementation of this interface.
    ```kotlin
    class LocalLibraryApiProvider(private val ktorClient: HttpClient) : ExternalSharingProvider { ... }
    class AnotherAppApiProvider(private val ktorClient: HttpClient) : ExternalSharingProvider { ... }
    ```
3.  **Repository Aggregation:** The main `ItemRepositoryImpl` will be injected with a list of all available `ExternalSharingProvider` implementations.
    ```kotlin
    class ItemRepositoryImpl(
        private val localDataSource: ItemLocalDataSource,
        private val firebaseDataSource: ItemRemoteDataSource, // Our own backend
        private val externalProviders: List<ExternalSharingProvider>
    ) : ItemRepository {

        override suspend fun searchCommunityItems(query: String): List<Item> {
            val firebaseResults = firebaseDataSource.search(query)
            val externalResults = externalProviders.flatMap { it.search(query) }
            return firebaseResults + externalResults
        }
    }
    ```
4.  **DI Configuration:** Dependency injection will be used to easily add or remove providers from the `externalProviders` list, effectively enabling or disabling integrations without changing the repository code.
