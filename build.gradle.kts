import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    `java-library`
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>{
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    testImplementation("org.jetbrains.kotlin:kotlin-test")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.23")
    testImplementation("org.hamcrest:hamcrest:2.2")
}
