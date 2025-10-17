# Library of Things

This project is a "Library of Things" application built with Kotlin Multiplatform.

## First Time Setup

1.  **Install JDK:** Make sure you have a recent version of the Java Development Kit (JDK) installed (e.g., JDK 17).
2.  **Install IntelliJ IDEA:** It is recommended to use IntelliJ IDEA with the Kotlin plugin.
3.  **Clone the repository:** `git clone <repository-url>`
4.  **Firebase Configuration:** You must add your own Firebase configuration files. Place your `google-services.json` in the `androidApp/` directory and your `GoogleService-Info.plist` in the `iosApp/` directory.
5.  **iOS Firebase Setup:** For iOS, you need to integrate the native Firebase SDKs.
    *   **Create Xcode Project:** If you don't have one, you need to create a basic Xcode project in the `iosApp` directory. 
        1.  Open Xcode, select `File > New > Project...`.
        2.  Choose the `iOS > App` template.
        3.  Name the project `iosApp` and save it in the `iosApp` directory of your project.
    *   **Create Podfile:** If you don't have one, create a `Podfile` in the `iosApp` directory with the necessary Firebase pods. A basic example:
        ```ruby
        platform :ios, '13.0'
        target 'iosApp' do
          use_frameworks!
          pod 'Firebase/Core'
          pod 'Firebase/Auth'
          pod 'Firebase/Database'
        end
        ```
    *   Navigate to the `iosApp` directory: `cd iosApp`
    *   Install CocoaPods dependencies: `pod install`
    *   Open the `.xcworkspace` file in Xcode.

6.  **Build the project:** Open the project in IntelliJ IDEA and build it. This will download all the necessary dependencies.

## Development

### Common Gradle Commands

*   **Build the entire project:**
    ```bash
    ./gradlew build
    ```
*   **Run all unit tests:**
    ```bash
    ./gradlew test
    ```
*   **Run the Android application (in debug mode):**
    ```bash
    ./gradlew :androidApp:assembleDebug
    ```
*   **Run the Desktop application:**
    ```bash
    ./gradlew :desktopApp:run
    ```
