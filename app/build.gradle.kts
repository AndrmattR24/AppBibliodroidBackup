plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.andrmatt.appbibliodroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.andrmatt.appbibliodroid"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures{
        viewBinding = true;
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Dependencies Firebase (Auth +  Database)
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-database:20.3.0")

    //Dependencies RoomDatabase
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    //Dependency GSON (Control JSON Retrofit)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Dependency Retrofit (For Consume API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Dependency Picasso (Control Images)
    implementation("com.squareup.picasso:picasso:2.71828")

    //Dependecy Lotitie (Images Format Json)
    //implementation 'com.airbnb.android:lottie:$lottieVersion'

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}