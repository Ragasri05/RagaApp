plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodorderapp"
    compileSdk = 34

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
            isMinifyEnabled = false
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

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Image Loading using PICASSO
    implementation(libs.picasso)
}