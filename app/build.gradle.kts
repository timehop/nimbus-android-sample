plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs") version("2.4.1")
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    compileSdk = 32

    defaultConfig {
        applicationId = "com.adsbynimbus.android.sample"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.11.5"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "PUBLISHER_KEY", "\"${property("sample_publisher_key")}\"")
        buildConfigField("String", "API_KEY", "\"${property("sample_api_key")}\"")
    }

    findProject(":internal")?.let { dynamicFeatures += setOf(":internal") }

    kotlinOptions {
        jvmTarget = "11"
    }
}

/* The following shows how to make keys configured by the build accessible in the sample app */
androidComponents.onVariants { variant ->
    variant.buildConfigFields.put(
        "PUBLISHER_KEY", providers.gradleProperty("sample_publisher_key").map { key ->
            com.android.build.api.variant.BuildConfigField(
                "String",
                "\"$key\"",
                "Publisher key required to initialize Nimbus"
            )
        }
    )
    variant.buildConfigFields.put(
        "API_KEY", providers.gradleProperty("sample_api_key").map { key ->
            com.android.build.api.variant.BuildConfigField("String",
                "\"$key\"",
                "Api key required to initialize Nimbus")
        }
    )
    // Other keys that can be configured in the sample app
    listOf(
        "sample_aps_app_key",
        "sample_aps_banner",
        "sample_aps_static",
        "sample_aps_video",
        "sample_fan_native_id",
        "sample_fan_interstitial_id",
        "sample_fan_banner_320_id",
        "sample_fan_native_320_id",
        "sample_gam_placement_id",
        "sample_unity_game_id",
    ).forEach {
        variant.buildConfigFields.put(
            it.substringAfter("sample_").toUpperCase(),
            providers.gradleProperty(it).map { key ->
                com.android.build.api.variant.BuildConfigField("String",
                    "\"$key\"",
                    "")
            },
        )
    }
}

dependencies {
    api("androidx.startup:startup-runtime:1.1.1")

    // Nimbus
    val nimbusVersion = "1.11.5"
    api("com.adsbynimbus.android:nimbus:$nimbusVersion")
    api("com.adsbynimbus.android:extension-aps:$nimbusVersion")
    api("com.adsbynimbus.android:extension-exoplayer:$nimbusVersion")
    api("com.adsbynimbus.android:extension-facebook:$nimbusVersion")
    api("com.adsbynimbus.android:extension-google:$nimbusVersion")
    api("com.adsbynimbus.android:extension-okhttp:$nimbusVersion")
    api("com.adsbynimbus.android:extension-viewability:$nimbusVersion")
    api("com.adsbynimbus.android:extension-unity:$nimbusVersion")

    // Aps
    api("com.amazon.android:aps-sdk:9.5.3")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Facebook
    api("com.facebook.android:audience-network-sdk:6.11.0")

    // Google
    api("com.google.android.gms:play-services-ads:21.0.0")

    // Core, Fragment, AppCompat
    api("androidx.appcompat:appcompat:1.4.2")
    api("androidx.core:core-ktx:1.8.0")
    api("androidx.fragment:fragment-ktx:1.4.1")

    // Navigation
    api("androidx.navigation:navigation-fragment-ktx:2.4.2")
    api("androidx.navigation:navigation-runtime-ktx:2.4.2")
    api("androidx.navigation:navigation-ui-ktx:2.4.2")

    // Preferences
    api("androidx.preference:preference-ktx:1.2.0")

    // RecyclerView
    api("androidx.recyclerview:recyclerview:1.2.1")

    // Material
    api("com.google.android.material:material:1.6.1")

    // OkHttp
    api("com.squareup.okhttp3:okhttp:4.10.0")
    api("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Timber
    api("com.jakewharton.timber:timber:5.0.1")

    constraints {
        api("androidx.activity:activity:1.4.0")
        api("androidx.activity:activity-ktx:1.4.0")
        api("androidx.annotation:annotation:1.4.0")
        api("androidx.annotation:annotation-experimental:1.2.0")
        api("androidx.browser:browser:1.4.0")
        api("androidx.collection:collection:1.2.0")
        api("androidx.collection:collection-ktx:1.2.0")
        api("androidx.constraintlayout:constraintlayout:2.1.3")
        api("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
        api("androidx.lifecycle:lifecycle-livedata:2.4.1")
        api("androidx.lifecycle:lifecycle-runtime:2.4.1")
        api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
        api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
        api("androidx.media2:media2-player:1.2.1")
        api("androidx.media2:media2-widget:1.2.1")
        api("androidx.media:media:1.6.0")
        api("androidx.room:room-runtime:2.4.2")
        api("androidx.transition:transition:1.4.1")
        api("androidx.work:work-runtime:2.7.1")
        api("com.google.android.gms:play-services-ads-identifier:18.0.1")
        api("com.google.ads.interactivemedia.v3:interactivemedia:3.27.1")
        api("com.squareup.okio:okio:3.1.0")
        api("org.jetbrains:annotations:23.0.0")
    }
}
