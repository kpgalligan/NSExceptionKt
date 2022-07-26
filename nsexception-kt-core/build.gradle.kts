plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    explicitApi()

    macosX64()
    macosArm64()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosX64()
    tvosSimulatorArm64()

    val commonMain by sourceSets.getting
    val commonTest by sourceSets.getting {
        dependencies {
            implementation(kotlin("test"))
        }
    }

    val nativeMain by sourceSets.creating
    nativeMain.dependsOn(commonMain)

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().all {
        val mainSourceSet = compilations.getByName("main").defaultSourceSet
        val testSourceSet = compilations.getByName("test").defaultSourceSet

        mainSourceSet.dependsOn(nativeMain)
        testSourceSet.dependsOn(commonTest)
    }
}

apply(from = "../gradle/gradle-mvn-mpp-push.gradle")