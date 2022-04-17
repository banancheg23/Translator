plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(Modules.model))

    implementation(Kotlin.core)

    //Design
    implementation(Design.appcompat)
    implementation(Design.material)
}