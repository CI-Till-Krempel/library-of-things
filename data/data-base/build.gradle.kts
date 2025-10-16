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
                implementation(libs.ktor.client.core)
                implementation(libs.firebase.database)
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
    namespace = "com.libraryofthings.data.base"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
