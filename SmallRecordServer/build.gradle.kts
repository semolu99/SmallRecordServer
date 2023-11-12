import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
}

group = "ConSilkTea"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("mysql:mysql-connector-java:8.0.32")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework:spring-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.ktor:ktor-server-core:1.6.4") // Core features for Ktor
    implementation("io.ktor:ktor-server-netty:1.6.4") // Netty as the engine for Ktor
    implementation("io.ktor:ktor-features:1.6.4") // Additional features for Ktor
    // For Content Negotiation with Jackson
    implementation("io.ktor:ktor-jackson:1.6.4") // For WebSockets
    implementation("io.ktor:ktor-websockets:1.6.4")
    // For Status Pages
    implementation("io.ktor:ktor-http:1.6.4")
    // For Duration class (used in your example)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
    implementation("io.ktor:ktor-websockets:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation("io.ktor:ktor-server-core:1.6.4") // Core features for Ktor
    implementation("io.ktor:ktor-server-netty:1.6.4") // Netty as the engine for Ktor
    implementation("io.ktor:ktor-features:1.6.4") // Additional features for Ktor
    // For Content Negotiation with Jackson
    implementation("io.ktor:ktor-jackson:1.6.4")
    // For WebSockets
    implementation("io.ktor:ktor-websockets:1.6.4")
    // For Status Pages
    implementation("io.ktor:ktor-http:1.6.4")
    // For Duration class (used in your example)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")
    implementation("io.ktor:ktor-websockets:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

}

allOpen {
    annotation("jarkta.persistence.Entity")
    annotation("jarkta.persistence.MappedSuperclass")
    annotation("jarkta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
