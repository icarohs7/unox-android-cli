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
    implementation(Deps.arrowCore)
    implementation(Deps.arrowEffects)
    implementation(Deps.arrowSyntax)
    implementation(Deps.arrowTypeclasses)
    implementation(Deps.clikt)
    implementation(Deps.koinCore)

    implementation("org.eclipse.jgit:org.eclipse.jgit:5.3.0.201903130848-r")

    TestDeps.core.forEach(::testImplementation)
}

application {
    mainClassName = "com.github.icarohs7.AppKt"
}
