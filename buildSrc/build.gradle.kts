plugins {
    `kotlin-dsl`
}

dependencies {
    val kotlinVersion = "1.3.21"

    compileOnly(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
}

repositories {
    google()
    jcenter()
}