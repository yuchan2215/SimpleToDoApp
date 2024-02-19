plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidGradlePlugin).apply(false)
    alias(libs.plugins.androidGradleLibraryPlugin).apply(false)
    alias(libs.plugins.kotlinGradlePlugin).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
}
