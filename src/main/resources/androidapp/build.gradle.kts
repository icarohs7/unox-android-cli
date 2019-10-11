plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    defaults.`android-module`
}

android {
    defaultConfig {
        applicationId = "{{module.completeName}}"
        versionCode = 1
        versionName = "1.00"
    }
}

dependencies {
    implementation(res("data"))
    implementation(res("drawer"))

    AndroidKaptDeps.core.forEach(::kapt)
}