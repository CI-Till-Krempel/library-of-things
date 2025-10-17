plugins {
    id("org.jetbrains.compose")
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

compose {
    desktop {
        application {
            mainClass = "MainKt"
        }
    }
}
