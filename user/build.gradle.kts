tasks {
    application {
        mainClass.set("com.starter.user.UserApplicationKt")
    }

    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}

dependencies {
    implementation(project(":core"))
    testImplementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
