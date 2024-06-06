plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(project(":common"))

    implementation(libs.postgresql)
    implementation(libs.bundles.logs)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.jsonSchema)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
