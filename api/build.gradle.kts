tasks {
    application {
        mainClass.set("com.starter.api.ApiApplicationKt")
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

}
