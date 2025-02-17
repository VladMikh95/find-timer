plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    //app namespace
    namespace = "ml.vladmikh.projects.find_timer.android"
    compileSdk = 34
    defaultConfig {
        //unique id for Google Play
        applicationId = "ml.vladmikh.projects.find_timer.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    //For the release version
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveable Api",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }

    dependencies {
        // Android depends on the shared module
        implementation(project(":shared"))
        // Android specific dependencies
        implementation(platform(libs.compose.bom))
        implementation(libs.bundles.compose.ui)
        implementation(libs.bundles.androidx.activity)
        implementation(libs.bundles.material)
        implementation(libs.napier)
    }

}

