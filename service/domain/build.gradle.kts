plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(project(":common"))
    implementation(libs.bundles.logs)
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.exposed)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
