# Implementation Plan: Protect Google Service Account Credentials

**Objective:** Remove the `google-services.json` and `GoogleService-Info.plist` files from the repository and instead generate them at build time from a secure environment variable.

**Rationale:** Storing credentials and sensitive information in a repository is a security risk. This plan mitigates that risk by keeping the credentials out of the codebase.

**Steps:**

1.  **Remove `google-services.json` and `GoogleService-Info.plist` from Git:**
    *   Add `google-services.json` and `GoogleService-Info.plist` to the `.gitignore` file.
    *   Use `git rm --cached` to remove the files from the index.

2.  **Create a Base64 encoded environment variable:**
    *   Locally, convert the `google-services.json` and `GoogleService-Info.plist` files to Base64 strings.
    *   Store these strings in a secure environment variable (e.g., in a `.env` file that is not checked into the repository, or directly in the CI/CD settings).

3.  **Modify the build process:**
    *   **Android:** In the `androidApp/build.gradle.kts` file, add a task that decodes the Base64 environment variable and creates the `google-services.json` file before the `processGoogleServices` task runs.
    *   **iOS:** In the `iosApp/Podfile`, add a pre-install hook that decodes the Base64 environment variable and creates the `GoogleService-Info.plist` file.

4.  **Update Documentation:**
    *   Update the `README.md` to instruct developers on how to set up the required environment variables for local development.

5.  **CI/CD Integration:**
    *   Configure the CI/CD pipeline to securely provide the environment variables to the build agents.
