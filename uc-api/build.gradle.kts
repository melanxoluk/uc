plugins {
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation(project(":uc-domain"))

    implementation(libs.exposedCore)
    implementation(libs.bundles.logs)
    implementation(libs.bundles.http4k)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
