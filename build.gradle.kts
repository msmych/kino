plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

repositories {
    mavenCentral()
}

val assertJVersion: String by project
val jupiterVersion: String by project
val kotlinLoggingVersion: String by project
val kotlinxSerializationVersion: String by project

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    dependencies {
        implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

        testImplementation("org.assertj:assertj-core:$assertJVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
    }

    repositories {
        mavenCentral()
    }

    kotlin {
        jvmToolchain(17)
    }

    tasks.test {
        useJUnitPlatform()
    }
}
