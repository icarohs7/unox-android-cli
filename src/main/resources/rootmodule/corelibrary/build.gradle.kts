plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    defaults.`android-module`
}

android {
    defaultSettings()
}

dependencies {
    api(Deps.arrowData)
    api(Deps.arrowInstancesData)
    api(Deps.arrowSyntax)
    api(Deps.arrowTypeclasses)
    api(Deps.gson)
    api(Deps.jodaTime)
    api(Deps.kodaTime)

    api(AndroidDeps.circularImageView)
    api(AndroidDeps.kotprefGson)
    api(AndroidDeps.maskedEditText)
    api(AndroidDeps.materialDialogsDateTime)
    api(AndroidDeps.materialDrawer)
    api(AndroidDeps.materialDrawerKt)
    api(AndroidDeps.materialDrawerMaterialTypeface)
    api(AndroidDeps.materialDrawerMaterialTypefaceOriginal)
    api(AndroidDeps.navigationFragment)
    api(AndroidDeps.navigationUi)
    api(AndroidDeps.rxBinding)
    api(AndroidDeps.rxBindingAppCompat)
    api(AndroidDeps.rxBindingCore)
    api(AndroidDeps.rxBindingMaterial)
    api(AndroidDeps.rxBindingRecyclerView)
    api(AndroidDeps.splittiesLifecycleCoroutines)
    api(AndroidDeps.splittiesViewsDsl)
    api(AndroidDeps.splittiesViewsDslAppcompat)
    api(AndroidDeps.splittiesViewsDslMaterial)
    api(AndroidDeps.unoxAndroidArch)
}