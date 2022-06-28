@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        if (providers.environmentVariable("GITHUB_ACTIONS").isPresent) {
            // If running in Github Actions, use Github packages because it's free
            maven {
                url = uri("https://maven.pkg.github.com/timehop/nimbus-openrtb")
                name = "openrtb"
                credentials(PasswordCredentials::class)
                content {
                    includeGroup("com.adsbynimbus.openrtb")
                }
            }
        }
        maven {
            url = uri("https://adsbynimbus-public.s3.amazonaws.com/android/sdks")
            credentials {
                username = "*"
            }
            content {
                includeGroup("com.adsbynimbus.android")
                includeGroup("com.adsbynimbus.openrtb")
                includeGroup("com.iab.omid.library.adsbynimbus")
            }
        }
    }
}

rootProject.name = "nimbus-android-sample"

include("app")

if (file("../internal").exists()) includeFlat("internal")

includeBuild("..") {
    dependencySubstitution {
        substitute(module("com.adsbynimbus.android:nimbus")).using(project(":library:all"))
        substitute(module("com.adsbynimbus.android:nimbus-core")).using(project(":library:core"))
        substitute(module("com.adsbynimbus.android:nimbus-request")).using(project(":library:request"))
        substitute(module("com.adsbynimbus.android:nimbus-static")).using(project(":library:static"))
        substitute(module("com.adsbynimbus.android:nimbus-ui")).using(project(":library:ui"))
        substitute(module("com.adsbynimbus.android:nimbus-video")).using(project(":library:video"))
        substitute(module("com.adsbynimbus.android:extension-aps")).using(project(":extensions:aps"))
        substitute(module("com.adsbynimbus.android:extension-exoplayer")).using(project(":extensions:exoplayer"))
        substitute(module("com.adsbynimbus.android:extension-facebook")).using(project(":extensions:facebook"))
        substitute(module("com.adsbynimbus.android:extension-google")).using(project(":extensions:google"))
        substitute(module("com.adsbynimbus.android:extension-okhttp")).using(project(":extensions:okhttp"))
        substitute(module("com.adsbynimbus.android:extension-unity")).using(project(":extensions:unity"))
        substitute(module("com.adsbynimbus.android:extension-viewability")).using(project(":extensions:viewability"))
    }
}
