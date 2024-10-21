import extension.implementations
import extension.kapts

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.tcp.taskmanagement"
    compileSdk = projectConfig.AndroidConfig.compileSdkVersion

    defaultConfig {
        minSdk = projectConfig.AndroidConfig.minSdkVersion
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", ".aar"))))

    implementation(project(path = projectDependencies.Modules.CORE_NETWORK))
    implementation(project(path = projectDependencies.Modules.CORE_UI))

    implementations(projectDependencies.ProjectDependencies.coreUI)
    implementations(projectDependencies.ProjectDependencies.lifecycle)
    implementations(projectDependencies.ProjectDependencies.sdpDependency)
    implementations(projectDependencies.ProjectDependencies.di)
    implementations(projectDependencies.ProjectDependencies.networking)

    kapts(projectDependencies.ProjectDependencies.processing)
}