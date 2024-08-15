tasks {
    application {
        mainClass.set("com.starter.file.FileApplicationKt")
    }

    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}

dependencies {
    implementation(project(":server:core"))
    testImplementation(project(":server:core"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
