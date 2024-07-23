plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.dev.tools.ksp)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
}

android {
    namespace = "com.heyrex.myculqiapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.heyrex.myculqiapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "HOST_API", "\"https://reqres.in\"")
            buildConfigField("okhttp3.logging.HttpLoggingInterceptor.Level", "LEVEL_LOGS", "okhttp3.logging.HttpLoggingInterceptor.Level.NONE")
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "HOST_API", "\"https://reqres.in\"")
            buildConfigField("okhttp3.logging.HttpLoggingInterceptor.Level", "LEVEL_LOGS", "okhttp3.logging.HttpLoggingInterceptor.Level.BODY")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.coil.compose)
    implementation(libs.swipe.refresh)
    implementation(libs.crypto)

    // Test mockito
    testImplementation(libs.mockito)
    androidTestImplementation(libs.mockitoAndroid)
    testImplementation(libs.mockitoKotlin)

    // Test MockWebServer
    testImplementation(libs.mockk)
    testImplementation(libs.coroutineTest)

    implementation("androidx.compose.material:material-icons-extended:1.0.0")
}