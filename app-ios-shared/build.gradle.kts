import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("simpletodoapp.primitive.kmp")
    id("simpletodoapp.primitive.kmp.ios")
    id("simpletodoapp.primitive.detekt")
}

kotlin {
    val frameworkName = "shared"
    val xcf = XCFramework(frameworkName)

    targets.filterIsInstance<KotlinNativeTarget>().forEach {
        it.binaries {
            framework {
                baseName = frameworkName
                isStatic = true
                embedBitcode(BitcodeEmbeddingMode.DISABLE)

                xcf.add(this)

                export(projects.core.data)
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(projects.core.data)
            }
        }
    }
}
