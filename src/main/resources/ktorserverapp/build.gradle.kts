plugins {
    application
    kotlin("jvm")
    id("com.github.ben-manes.versions")
    id("com.github.johnrengelman.shadow") version "5.1.0"
    defaults.`jvm-module`
}

group = "{{module.group}}"
version = "0.0-dev01"

application {
    mainClassName = "{{module.completeName}}.ApplicationKt"
}

dependencies {
    implementation(Deps.kotlinStdLibJdk8)
    implementation(Deps.ktorServerCore)
    implementation(Deps.ktorServerNetty)
    implementation(Deps.ktorServerSerialization)
    implementation(Deps.logbackClassic)
    implementation(Deps.unoxCoreJvm)

    TestDeps.core.forEach(::testImplementation)
    testImplementation(TestDeps.ktorServer)
}
