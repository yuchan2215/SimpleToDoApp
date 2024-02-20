plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlinGradlePlugin)
    id("simpletodoapp.primitive.detekt")
    id("simpletodoapp.primitive.android.application")
}

android {
    namespace = "xyz.miyayu.simpletodoapp.android"
    defaultConfig {
        applicationId = "xyz.miyayu.simpletodoapp.android"
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)
}
