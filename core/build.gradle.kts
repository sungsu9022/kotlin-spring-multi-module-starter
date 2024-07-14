tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}

dependencies {
    val awsJavaSdkVersion = "1.12.634"
    val awsBomVersion = "2.22.9"
    val springCloudStarterAwsVersion = "2.2.6.RELEASE"

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // s3
    implementation(platform("software.amazon.awssdk:bom:$awsBomVersion"))
    implementation("org.springframework.cloud:spring-cloud-starter-aws:$springCloudStarterAwsVersion")
    implementation("com.amazonaws:aws-java-sdk-sts:$awsJavaSdkVersion")

    // httpClient
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.netty:reactor-netty-http")
    implementation("io.netty:netty-handler")





    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
