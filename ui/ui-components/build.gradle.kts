plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.android.library")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    desktop()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":ui:ui-base"))
            }
        }
    }
}

android {
    namespace = "com.libraryofthings.ui.components"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
