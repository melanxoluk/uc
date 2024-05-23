plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(libs.bundles.logs)
    implementation(libs.bundles.jackson)
    implementation(libs.exposed.core)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
