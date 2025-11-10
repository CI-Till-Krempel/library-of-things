plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

repositories {
    google()
    mavenCentral()
}

val createGoogleServices = tasks.register("createGoogleServices", Exec::class) {
    commandLine("../scripts/create-google-services.sh")
}

afterEvaluate {
    tasks.named("processDebugGoogleServices").configure {
        dependsOn(createGoogleServices)
    }
    tasks.named("processReleaseGoogleServices").configure {
        dependsOn(createGoogleServices)
    }
}

android {
    namespace = "com.libraryofthings.android"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.libraryofthings.android"
        minSdk = 24
        targetSdk = 36
        
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(compose.runtime)
}