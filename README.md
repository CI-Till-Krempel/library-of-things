# Library of Things

This project is a "Library of Things" application built with Kotlin Multiplatform.

## First Time Setup

1.  **Install JDK:** Make sure you have a recent version of the Java Development Kit (JDK) installed (e.g., JDK 17).
2.  **Install IntelliJ IDEA:** It is recommended to use IntelliJ IDEA with the Kotlin plugin.
3.  **Clone the repository:** `git clone <repository-url>`
4.  **Firebase Configuration:** To configure Firebase, you need to provide your `google-services.json` and `GoogleService-Info.plist` files as base64 encoded environment variables.
    *   **Base64 Encode your Firebase files:**
        ```bash
        cat androidApp/google-services.json | base64 > google-services.json.base64
        cat iosApp/GoogleService-Info.plist | base64 > GoogleService-Info.plist.base64
        ```
    *   **Set Environment Variables:** Set the content of these base64 files as environment variables named `GOOGLE_SERVICES_JSON` and `GOOGLE_SERVICES_PLIST` respectively. For example, in your `.bashrc` or `.zshrc`:
        ```bash
        export GOOGLE_SERVICES_JSON="$(cat google-services.json.base64)"
        export GOOGLE_SERVICES_PLIST="$(cat GoogleService-Info.plist.base64)"
        ```
    *   **Important:** Ensure these environment variables are set in your development environment and any CI/CD pipelines.
        For local development, you can add them to your `.bashrc` or `.zshrc` file or create an .env file that you run with "source <PROJECT_DIR>/.env".
        For CI/CD pipelines, you can add them to your CI/CD secrets.
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
