plugins {
    kotlin("jvm") version "1.9.0"
    id("org.springframework.boot") version "3.0.6"
}

repositories { mavenCentral() }
kotlin { jvmToolchain(17) }
tasks.test { useJUnitPlatform() }

apply(plugin = "io.spring.dependency-management")

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}