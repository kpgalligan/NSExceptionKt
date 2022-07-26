buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.18.0")
    }
}

val GROUP: String by project
val VERSION_NAME: String by project

allprojects {
    group = GROUP
    version = VERSION_NAME

    repositories {
        mavenCentral()
    }
}
