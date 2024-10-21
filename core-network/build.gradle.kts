import extension.implementations
import extension.kapts

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.core_network"
    compileSdk = projectConfig.AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = projectConfig.AndroidConfig.minSdkVersion
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

    dexOptions{
        jumboMode = true
        javaMaxHeapSize = "4g"
    }
}

dependencies {
     implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", ".aar"))))

        implementations(projectDependencies.ProjectDependencies.logging)
        implementations(projectDependencies.ProjectDependencies.networking)
        implementations(projectDependencies.ProjectDependencies.di)

        kapts(projectDependencies.ProjectDependencies.processing)
}

kapt {
    correctErrorTypes = true
}

