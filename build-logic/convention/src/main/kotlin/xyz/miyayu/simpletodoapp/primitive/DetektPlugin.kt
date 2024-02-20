package xyz.miyayu.simpletodoapp.primitive

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import xyz.miyayu.simpletodoapp.primitive.dsl.libs
import xyz.miyayu.simpletodoapp.primitive.dsl.setupDetekt

class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            setupDetekt(extensions.getByType<DetektExtension>())

            dependencies {
                "detektPlugins"(libs.findLibrary("detekt.formatting").get())
                "detektPlugins"(libs.findLibrary("detekt.twitterComposeRules").get())
            }
        }
    }
}
