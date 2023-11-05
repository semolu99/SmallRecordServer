import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5" //스프링 부트 사용하기 위한 플러그인
    id("io.spring.dependency-management") version "1.1.3" //버전 관리
    kotlin("jvm") version "1.8.22" //jvm에 의해서 실행
    kotlin("plugin.spring") version "1.8.22" //스프링 부트 사용
    kotlin("plugin.jpa") version "1.8.22" //jpa 사용
}

group = "ConSilkTea"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {//의존성 관리
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("mysql:mysql-connector-java:8.0.26") //MySQL 삽입
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework:spring-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")


}

allOpen {//기본 적으로 오픈 되지 않은 annotation 추가
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
