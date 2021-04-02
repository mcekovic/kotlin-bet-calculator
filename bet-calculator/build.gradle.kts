plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = "org.strangeforest"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    when {
        hostOs == "Linux" -> linuxX64("native") {
            binaries {
                sharedLib()
            }
        }
        hostOs.startsWith("Windows") -> mingwX64("native") {
            binaries {
                sharedLib()
            }
        }
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("com.willowtreeapps.assertk:assertk:0.23.1") {
                    exclude("org.jetbrains.kotlin", "*")
                }
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}
