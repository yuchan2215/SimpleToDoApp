package xyz.miyayu.simpletodoapp.primitive.dsl

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

fun Project.setupDetekt(extension: DetektExtension) {
    extension.apply {
        // parallel processing
        parallel = true

        config.setFrom("${project.rootDir}/config/detekt/detekt.yml")
        baseline = file("${project.rootDir}/config/detekt/baseline.xml")

        buildUponDefaultConfig = true
        ignoreFailures = false

        autoCorrect = true
    }

    val reportMerge = if (!rootProject.tasks.names.contains("reportMerge")) {
        rootProject.tasks.register("reportMerge", ReportMergeTask::class) {
            output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml"))
            output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.html"))
        }
    } else {
        @Suppress("UNCHECKED_CAST")
        rootProject.tasks.named("reportMerge") as TaskProvider<ReportMergeTask>
    }

    plugins.withType<DetektPlugin> {
        tasks.withType<Detekt> detekt@{
            finalizedBy(reportMerge)

            source = project.files("./").asFileTree

            include("**/*.kt")
            include("**/*.kts")
            exclude("**/resources/**")
            exclude("**/build/**")

            reportMerge.configure {
                input.from(this@detekt.xmlReportFile) // or .sarifReportFile
                input.from(this@detekt.htmlReportFile)
            }
        }
    }
}
