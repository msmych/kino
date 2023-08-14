plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.flywaydb.flyway") version "9.21.1"
}

val postgresDriverVersion: String by project

dependencies {
    runtimeOnly("org.postgresql:postgresql:$postgresDriverVersion")
}

repositories {
    mavenCentral()
}

flyway {
    url = project.properties["kino.flyway.url"].toString()
    user = project.properties["kino.flyway.user"].toString()
    password = project.properties["kino.flyway.password"].toString()
    schemas = arrayOf("kino")
    driver = "org.postgresql.Driver"
}

val kotlinLoggingVersion: String by project
val jupiterVersion: String by project
val assertJVersion: String by project

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    dependencies {
        implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")

        testImplementation("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
        testImplementation("org.assertj:assertj-core:$assertJVersion")
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
