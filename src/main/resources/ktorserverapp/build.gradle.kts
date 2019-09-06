plugins {
    application
    kotlin("jvm")
    id("com.github.ben-manes.versions")
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

group = "{{module.group}}"
version = "0.0.0-next.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(Deps.kotlinStdLibJdk8)
    implementation(Deps.ktorServerCore)
    implementation(Deps.ktorServerNetty)
    implementation(Deps.logbackClassic)

    TestDeps.core.forEach(::testImplementation)
    testImplementation(TestDeps.ktorServer)
}
