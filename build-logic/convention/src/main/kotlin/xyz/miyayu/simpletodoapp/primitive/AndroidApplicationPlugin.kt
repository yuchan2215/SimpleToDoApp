package xyz.miyayu.simpletodoapp.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import xyz.miyayu.simpletodoapp.primitive.dsl.androidApplication
import xyz.miyayu.simpletodoapp.primitive.dsl.configureAndroid

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                configureAndroid()
            }
        }
    }
}
