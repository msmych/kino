plugins {
    application
}

val logbackVersion: String by project

dependencies {
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation(project(":movies"))
}

application {
    mainClass.set("uk.matvey.kino.AppKt")
}
