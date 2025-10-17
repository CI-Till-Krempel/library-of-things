# Technical Task: Working iOS Build

## 1. Goal

To successfully build and run the iOS application target, resolving all current compilation and linking errors, especially related to Firebase SDK integration.

## 2. Current State / Problem

The current iOS build fails with `ld: framework 'FirebaseCore' not found`. This indicates that the native Firebase iOS SDKs are not correctly linked with the Kotlin Multiplatform project.

## 3. Steps to Resolution

1.  **Verify `iosApp/Podfile`:** Ensure the `Podfile` in the `iosApp` directory correctly specifies the required Firebase pods (e.g., `Firebase/Core`, `Firebase/Auth`, `Firebase/Database`).
2.  **Run `pod install`:** Navigate to the `iosApp` directory and run `pod install` to download and integrate the Firebase SDKs.
3.  **Open `.xcworkspace`:** Always open the `.xcworkspace` file (not `.xcodeproj`) in Xcode after running `pod install`.
4.  **Build in Xcode:** Attempt to build the `iosApp` target directly from Xcode to identify any further native build issues.
5.  **Verify Gradle Integration:** Ensure the `shared` module's `build.gradle.kts` correctly configures the iOS targets and links with the CocoaPods.
6.  **Test Run:** Successfully run the iOS application on a simulator or device.

## 4. Verification

*   The `iosApp` builds successfully in Xcode.
*   The `iosApp` launches and runs without crashing on a simulator/device.
*   No `ld: framework 'FirebaseCore' not found` or similar linking errors.
