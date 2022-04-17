plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.utils))

    implementation(Design.appcompat)

    implementation(Kotlin.core)
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)


    testImplementation(Test.junit)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.espresso)

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}