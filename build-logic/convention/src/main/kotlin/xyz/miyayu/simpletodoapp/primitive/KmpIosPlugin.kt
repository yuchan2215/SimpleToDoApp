package xyz.miyayu.simpletodoapp.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import xyz.miyayu.simpletodoapp.primitive.dsl.kotlin

class KmpIosPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            kotlin {
                iosX64()
                iosArm64()
                iosSimulatorArm64()

                with(sourceSets) {
                    create("iosMain") {
                        dependsOn(getByName("commonMain"))
                        maybeCreate("iosArm64Main").dependsOn(this)
                        maybeCreate("iosX64Main").dependsOn(this)
                        maybeCreate("iosSimulatorArm64Main").dependsOn(this)
                    }

                    create("iosTest") {
                        dependsOn(getByName("commonTest"))
                        maybeCreate("iosArm64Main").dependsOn(this)
                        maybeCreate("iosX64Main").dependsOn(this)
                        maybeCreate("iosSimulatorArm64Main").dependsOn(this)
                    }
                }

                // KDocをObjective-Cに出力する
                // 参考: https://kotlinlang.org/docs/native-objc-interop.html#c-features
                targets.withType<KotlinNativeTarget> {
                    compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
                }
            }
        }
    }
}
