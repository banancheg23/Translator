import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.banancheg.translator"
    const val min_sdk = 23
    const val compile_sdk = 31
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    const val historyScreen = ":historyScreen"
}

object Versions {

    // Design
    const val appcompat = "1.4.1"
    const val material = "1.5.0"
    const val swipeRefreshLayout = "1.1.0"

    //Kotlin
    const val core = "1.7.0"
    const val coroutinesCore = "1.6.0"
    const val coroutinesAndroid = "1.6.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.0"
    const val coroutinesAdapter = "0.9.2"

    //Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"
    const val koinAndroidCompat = "3.1.2"

    //Room
    const val roomRuntime = "2.4.2"
    const val roomKtx = "2.4.2"
    const val roomCompiler = "2.4.2"

    //Glide
    const val glide = "4.11.0"
    const val glideCompiler = "4.11.0"

    //Test
    const val jUnit = "4.13.2"
    const val runner = "1.4.0"
    const val espressoCore = "3.4.0"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glideCompiler}"
}

object Test {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}