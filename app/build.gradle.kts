plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "com.carnava.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isTestCoverageEnabled = true
            buildConfigField("String", "APP_DATABASE_NAME", "\"app_database\"")
        }

        getByName("release") {
            applicationIdSuffix = ".release"
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "APP_DATABASE_NAME", "\"app_database\"")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // KotlinK
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.32")

    // AndroidX
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    // Android UI
    implementation("androidx.fragment:fragment-ktx:1.3.3")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.recyclerview:recyclerview:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    // External UI
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Tool Navigation
    val alligator = "4.1.0"
    implementation("com.github.aartikov.Alligator:alligator:$alligator")
    kapt("com.github.aartikov.Alligator:alligator-compiler:$alligator")
    // Tool Database
    val room = "2.3.0-beta03"
    implementation("androidx.room:room-ktx:$room")
    kapt("androidx.room:room-compiler:$room")
    // Tool Prefs
    implementation("com.chibatching.kotpref:kotpref:2.13.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}