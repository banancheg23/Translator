plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.utils))

    //Kotlin
    implementation(Kotlin.core)

    // Coroutines
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    // Retrofit 2
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.interceptor)
    implementation(Retrofit.coroutines_adapter)

    //Room
    implementation(Room.room_runtime)
    implementation(Room.room_ktx)
    kapt(Room.room_compiler)

    // Test
    testImplementation(Test.junit)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.espresso)
}