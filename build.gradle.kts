plugins {
    val androidGradleVersion = "7.2.1"
    id("com.android.application") version(androidGradleVersion) apply(false)
    id("com.android.dynamic-feature") version(androidGradleVersion) apply(false)
    kotlin("android") version("1.7.0") apply(false)
}
