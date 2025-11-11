plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    androidTarget()

    // Nur moderne 64-bit iOS-Targets
    iosX64()           // Intel-basierte Simulatoren (ältere Macs)
    iosArm64()         // Echte iOS-Geräte (iPhone/iPad)
    iosSimulatorArm64() // Apple Silicon Simulatoren (M1/M2/M3 Macs)

    // /TODO: For iOS, ensure native Firebase SDKs are integrated via CocoaPods or SPM.
    // See README.md for instructions.

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.firebase.database)
                implementation(libs.firebase.auth)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val androidMain by getting {
            dependencies {
                // Required for androidx.lifecycle.viewModelScope extension
                implementation(libs.lifecycle.viewmodel.ktx)
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(libs.ktor.client.mock)
            }
        }
    }
}

android {
    namespace = "com.libraryofthings.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
