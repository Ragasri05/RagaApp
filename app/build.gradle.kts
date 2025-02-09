plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodorderapp"
    compileSdk = 34

    // basic configuration that applies to the app by default.
    defaultConfig {
        applicationId = "com.example.foodorderapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            //removal of unused code
            isMinifyEnabled = false
            // proguard helps reduce the size of the app and makes it difficult for reverse engineering it
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // firebase authentication
    implementation(libs.firebase.auth)

    //recyclerView dependency
    implementation(libs.recyclerview)
    //cardView
    implementation(libs.cardview)


    //RoomDatabase
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler) // For Kotlin projects
    // Optional: If you need Room with RxJava or Coroutines support
    implementation(libs.androidx.room.ktx)

    // To generate a Url.
    implementation (libs.nanohttpd)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit)

    // Gson converter for Retrofit
    implementation (libs.converter.gson)
    implementation (libs.converter.gson)

    implementation (libs.gson)



    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Image Loading using PICASSO
    implementation(libs.picasso)
}