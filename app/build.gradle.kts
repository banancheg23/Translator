plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = Config.application_id
        versionCode = Releases.version_code
        versionName = Releases.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes{
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"  )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(Modules.model))
    implementation(project(Modules.utils))
    implementation(project(Modules.core))
    implementation(project(Modules.historyScreen))
    implementation(project(Modules.repository))

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

// Koin
    implementation(Koin.koin_core)
    implementation(Koin.koin_android)
    implementation(Koin.koin_android_compat)

//Room
    implementation(Room.room_runtime)
    implementation(Room.room_ktx)
    kapt(Room.room_compiler)

//Glide
    implementation(Glide.glide)
    kapt(Glide.glide_compiler)

//Design
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(Design.swipe_refresh_layout)

// Test
    testImplementation(Test.junit)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.espresso)
}