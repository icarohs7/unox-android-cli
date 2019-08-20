plugins {
    kotlin("jvm")
    application
    defaults.`jvm-module`
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(Deps.arrowCoreData)
    implementation(Deps.coroutinesCore)
    implementation(Deps.koinCore)
    implementation(Deps.kotlinStdLibJdk8)

    implementation(JavaFxDeps.coroutinesJavaFx)
    implementation(JavaFxDeps.tornadoFx)
    implementation(JavaFxDeps.unoxJavafxArchCore)
}

application {
    mainClassName = "{{module.completeName}}.AppMain"
}