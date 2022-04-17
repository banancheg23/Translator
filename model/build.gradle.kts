plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {

    implementation(Retrofit.converter_gson)

    //Kotlin
    implementation(Kotlin.core)
}