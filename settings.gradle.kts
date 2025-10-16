pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "library-of-things"

include(":androidApp")
include(":desktopApp")
include(":shared")