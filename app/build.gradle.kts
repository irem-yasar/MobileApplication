plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.mobileapps"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mobileapps"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    implementation(libs.androidx.fragments)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Mockito for mocking Context
    testImplementation("org.mockito:mockito-core:4.8.1")  // Replace with the latest version
    testImplementation("org.mockito:mockito-inline:4.8.1")  // If you need inline mock functionality

    // JUnit for unit testing
    testImplementation("junit:junit:4.13.2")  // Or the latest version

    // Kotlin Standard Library for test support
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0")  // Or the latest version
}
