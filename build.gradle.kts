plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.shm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0") // kotlin junit 처럼 쓸 수 있는 Spec 들이 정의 됨
    testImplementation("io.kotest:kotest-assertions-core:5.8.0") // shouldBe... etc 와같이 Assertions 의 기능을 제공
//    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}
