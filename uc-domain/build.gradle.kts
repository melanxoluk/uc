plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(project(":uc-common"))

    implementation(libs.postgresql)
    implementation(libs.java.diff.utils)
    implementation(libs.bundles.logs)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.jsonSchema)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
