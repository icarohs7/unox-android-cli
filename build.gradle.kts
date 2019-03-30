plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.clikt)

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

application {
    mainClassName = "com.github.icarohs7.AppKt"
}
