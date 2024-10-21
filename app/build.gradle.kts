import extension.*
import projectConfig.*
import projectDependencies.Modules
import projectDependencies.ProjectDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AndroidConfig.applicationId
    compileSdk = AndroidConfig.compileSdkVersion

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdkVersion
        targetSdk = AndroidConfig.targetSdkVersion
        versionCode = getVersionCode()
        versionName = getVersionName()

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val envConfig = rootProject.getDevEnvConfig()
        buildConfigField("String", "apiBaseUrl", envConfig.apiBaseUrl)
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

    lint {
        abortOnError = false
        htmlReport = true
    }

    dexOptions {
        jumboMode = true
        javaMaxHeapSize = "4g"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    configurations {
        all { exclude(module = "javax.annotation") }
    }
    configurations.all {
        resolutionStrategy {
            exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
            exclude(group = "org.mockito", module = "mockito-inline")
            exclude(group = "org.mockito", module = "mockito-android")
        }
    }

//    flavorDimensions.add("env")
//    productFlavors {
//        create("dev") {
//            dimension = "env"
//
//            applicationIdSuffix = ".dev"
//            versionNameSuffix = "_DEV"
//
//            val envConfig = rootProject.getDevEnvConfig()
//            buildConfigField("String", "apiBaseUrl", envConfig.apiBaseUrl)
//        }
//        create("staging") {
//            dimension = "env"
//
//            applicationIdSuffix = ".stg"
//            versionNameSuffix = "_STG"
//
//            val envConfig = rootProject.getStgEnvConfig()
//            buildConfigField("String", "apiBaseUrl", envConfig.apiBaseUrl)
//        }
//        create("prod") {
//            dimension = "env"
//
//            val envConfig = rootProject.getProdEnvConfig()
//            buildConfigField("String", "apiBaseUrl", envConfig.apiBaseUrl)
//        }
//    }
}

dependencies {

    implementation(project(path = Modules.CORE_NETWORK))
    implementation(project(path = Modules.CORE_UI))
    implementation(project(path = Modules.FEATURE_TASK_MANAGEMENT))

    implementations(ProjectDependencies.coreUI)
    implementations(ProjectDependencies.di)
    implementations(ProjectDependencies.networking)

    kapts(projectDependencies.ProjectDependencies.processing)
}

kapt {
    correctErrorTypes = true
}