plugins {
    kotlin("jvm")
    defaults.`jvm-module`
}

compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    api(Deps.arrowCoreData)
    api(Deps.coroutinesCore)
    api(Deps.kotlinStdLibJdk8)
}
