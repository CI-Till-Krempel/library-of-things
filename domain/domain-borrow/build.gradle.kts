plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain:domain-base"))
                implementation(project(":domain:domain-item"))
                implementation(project(":domain:domain-user"))
            }
        }
    }
}

android {
    namespace = "com.libraryofthings.domain.borrow"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
