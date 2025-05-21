import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {

        // Old way of defining commonMain and commonTest
        val commonMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.no.arg)
                implementation(libs.kotlinx.serialization.json)
                // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
            }
        }

        // New way of defining commonMain and commonTest
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.kotlinx.serialization.json) // Or your desired version
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.gowthamraj07.tasklite"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
