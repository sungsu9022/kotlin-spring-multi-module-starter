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
    implementation(project(":core"))
    testImplementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-security")
}

apply("${project.rootDir}/webpack.build.gradle")
