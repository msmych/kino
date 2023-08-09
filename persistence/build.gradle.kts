val hikariCpVersion: String by project
val jooqVersion: String by project
val postgresDriverVersion: String by project

dependencies {
    api("com.zaxxer:HikariCP:$hikariCpVersion")
    api("org.jooq:jooq:$jooqVersion")

    runtimeOnly("org.postgresql:postgresql:$postgresDriverVersion")
}
