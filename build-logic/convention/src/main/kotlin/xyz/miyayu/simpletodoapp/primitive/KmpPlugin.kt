package xyz.miyayu.simpletodoapp.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink
import xyz.miyayu.simpletodoapp.primitive.dsl.configureKotlin

class KmpPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureKotlin()
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
            }

            tasks.withType<KotlinNativeLink>().configureEach {
                notCompatibleWithConfigurationCache(
                    "Configuration chache not supported for a system property read at configuration time",
                )
            }
        }
    }
}
