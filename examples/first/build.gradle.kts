plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(project(":service:client"))
    implementation(project(":service:domain"))
    
    implementation(libs.bundles.logs)
    implementation(libs.bundles.jackson)
    implementation(libs.kafka)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
