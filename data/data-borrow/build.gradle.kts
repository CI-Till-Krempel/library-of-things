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
                api(project(":data:data-base"))
                implementation(project(":domain:domain-borrow"))
            }
        }
    }
}

android {
    namespace = "com.libraryofthings.data.borrow"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
