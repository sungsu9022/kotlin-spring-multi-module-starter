tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = false
    }
}

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
