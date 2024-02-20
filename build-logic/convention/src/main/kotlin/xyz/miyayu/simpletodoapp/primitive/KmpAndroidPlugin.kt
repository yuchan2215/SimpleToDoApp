package xyz.miyayu.simpletodoapp.primitive

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import xyz.miyayu.simpletodoapp.primitive.dsl.android
import xyz.miyayu.simpletodoapp.primitive.dsl.configureAndroid
import xyz.miyayu.simpletodoapp.primitive.dsl.kotlin

class KmpAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            kotlin {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = JavaVersion.VERSION_17.toString()
                        }
                    }
                }
            }
            android {
                configureAndroid()
                sourceSets {
                    getByName("main") {
                        assets.srcDirs("src/androidMain/assets")
                        java.srcDirs("src/androidMain/kotlin", "src/commonMain/kotlin")
                        res.srcDirs("src/androidMain/res")
                    }
                    getByName("test") {
                        assets.srcDirs("src/androidUnitTest/assets")
                        java.srcDirs("src/androidUnitTest/kotlin", "src/commonTest/kotlin")
                        res.srcDirs("src/androidUnitTest/res")
                    }
                }
            }
        }
    }
}
