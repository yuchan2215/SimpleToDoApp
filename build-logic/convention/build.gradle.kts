import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "xyz.miyayu.simpletodoapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("detekt") {
            id = "simpletodoapp.primitive.detekt"
            implementationClass = "xyz.miyayu.simpletodoapp.primitive.DetektPlugin"
        }
        register("kmp") {
            id = "simpletodoapp.primitive.kmp"
            implementationClass = "xyz.miyayu.simpletodoapp.primitive.KmpPlugin"
        }
        register("kmpAndroid") {
            id = "simpletodoapp.primitive.kmp.android"
            implementationClass = "xyz.miyayu.simpletodoapp.primitive.KmpAndroidPlugin"
        }
        register("kmpiOS") {
            id = "simpletodoapp.primitive.kmp.ios"
            implementationClass = "xyz.miyayu.simpletodoapp.primitive.KmpIosPlugin"
        }

        register("androidApplication") {
            id = "simpletodoapp.primitive.android.application"
            implementationClass = "xyz.miyayu.simpletodoapp.primitive.AndroidApplicationPlugin"
        }
    }
}
