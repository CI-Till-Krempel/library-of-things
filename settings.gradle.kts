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
// iosApp and webApp are configured differently and not included here

include(":domain:domain-base")
include(":domain:domain-user")
include(":domain:domain-item")
include(":domain:domain-borrow")

include(":data:data-base")
include(":data:data-user")
include(":data:data-item")
include(":data:data-borrow")

include(":presentation:presentation-base")
include(":presentation:presentation-user")
include(":presentation:presentation-item")
include(":presentation:presentation-borrow")

include(":ui:ui-base")
include(":ui:ui-components")
include(":ui:ui-theme")
include(":ui:ui-user")
include(":ui:ui-item")
include(":ui:ui-borrow")
