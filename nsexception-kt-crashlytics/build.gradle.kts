plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    explicitApi()

    val macosX64 = macosX64()
    val macosArm64 = macosArm64()
    val iosArm64 = iosArm64()
    val iosX64 = iosX64()
    val iosSimulatorArm64 = iosSimulatorArm64()
    val watchosArm32 = watchosArm32()
    val watchosArm64 = watchosArm64()
    val watchosX64 = watchosX64()
    val watchosSimulatorArm64 = watchosSimulatorArm64()
    val tvosArm64 = tvosArm64()
    val tvosX64 = tvosX64()
    val tvosSimulatorArm64 = tvosSimulatorArm64()

    val commonMain by sourceSets.getting {
        dependencies {
            implementation(project(":nsexception-kt-core"))
        }
    }
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

    listOf(
        macosX64, macosArm64,
        iosArm64, iosX64, iosSimulatorArm64,
        watchosArm32, watchosArm64, watchosX64, watchosSimulatorArm64,
        tvosArm64, tvosX64, tvosSimulatorArm64
    ).forEach {
        it.compilations.getByName("main") {
            cinterops.create("FirebaseCrashlytics") {
                includeDirs("$projectDir/src/nativeInterop/cinterop/FirebaseCrashlytics")
            }
        }
    }
}

apply(from = "../gradle/gradle-mvn-mpp-push.gradle")