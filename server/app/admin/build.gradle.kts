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
    val jwtVersion = "0.11.5"

    implementation(project(":server:core"))
    testImplementation(project(":server:core"))

    implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")
    implementation("io.jsonwebtoken:jjwt-impl:$jwtVersion")
    implementation("io.jsonwebtoken:jjwt-jackson:$jwtVersion")
}
