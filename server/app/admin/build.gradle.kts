tasks {
    application {
        mainClass.set("com.starter.admin.AdminApplicationKt")
    }

    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}

dependencies {
    val flywayVersion = "9.21.1"

    implementation(project(":server:core"))
    testImplementation(project(":server:core"))

    // flyway
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.flywaydb:flyway-mysql:$flywayVersion")
}
