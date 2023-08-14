import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.JreVersion.JRE_17

plugins {
    id("io.ktor.plugin") version "2.3.2"
}

val logbackVersion: String by project

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-status-pages-jvm")

    implementation(project(":movies"))
}

application {
    mainClass.set("uk.matvey.kino.AppKt")
}

ktor {
    docker {
        jreVersion.set(JRE_17)
        localImageName.set("kino")
        imageTag.set("0.0.1")
        portMappings.add(DockerPortMapping(8000, 8080))
    }
}
