plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.utils))
    implementation(project(Modules.core))
    implementation(project(Modules.repository))

    //Kotlin
    implementation(Kotlin.core)

    // Coroutines
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    // Koin
    implementation(Koin.koin_core)
    implementation(Koin.koin_android)
    implementation(Koin.koin_android_compat)

    //Design
    implementation(Design.appcompat)
    implementation(Design.material)
}