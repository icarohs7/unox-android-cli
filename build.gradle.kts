import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

repositories {
    jcenter()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.arrowCoreData)
    implementation(Deps.clikt)
    implementation(Deps.koinCore)

    TestDeps.core.forEach(::testImplementation)
}

application {
    mainClassName = "com.github.icarohs7.AppKt"
}
