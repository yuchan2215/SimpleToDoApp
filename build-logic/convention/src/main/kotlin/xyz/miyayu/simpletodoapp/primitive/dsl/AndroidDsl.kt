package xyz.miyayu.simpletodoapp.primitive.dsl

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

private const val COMPILE_SDK_VERSION = 34
private const val MIN_SDK_VERSION = 23
private const val TARGET_SDK_VERSION = 33

fun Project.androidApplication(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}
fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.configureAndroid() {
    configureDesugar()
    android {
        namespace?.let {
            this.namespace = it
        }

        compileSdkVersion(COMPILE_SDK_VERSION)

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }

        (this as CommonExtension<*, *, *, *, *>).lint {
            // shell friendly
            val filename = displayName.replace(":", "_").replace("[\\s']".toRegex(), "")

            xmlReport = true
            xmlOutput =
                rootProject.layout.buildDirectory.file("lint-reports/lint-results-$filename.xml")
                    .get().asFile

            htmlReport = true
            htmlOutput =
                rootProject.layout.buildDirectory.file("lint-reports/lint-results-$filename.html")
                    .get().asFile

            // for now
            sarifReport = false
        }
    }
}

/**
 * Java8の言語機能を古いAndroid上で互換性を確保する。
 */
private fun Project.configureDesugar() {
    android {
        compileOptions.isCoreLibraryDesugaringEnabled = true
    }
    dependencies.add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
}
