rootProject.name = "uc"

include(":common")
include(":service:client")
include(":service:domain")
include(":examples:first")

// configure dependencies to all projects
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            library("kafka", "org.apache.kafka:kafka-clients:3.7.0")
            library("postgresql", "org.postgresql:postgresql:42.7.1")
            library("slf4j", "org.slf4j:slf4j-api:2.0.9")
            library("jsonSchemaValidator", "com.networknt:json-schema-validator:1.3.3")

            version("logback", "1.5.1")
            library("logbackCore", "ch.qos.logback", "logback-core").versionRef("logback")
            library("logbackClassic", "ch.qos.logback", "logback-classic").versionRef("logback")
            bundle("logs", listOf("slf4j", "logbackCore", "logbackClassic"))

            library("jacksonKotlin", "com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
            library("jacksonJsr", "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.1")
            library("jacksonYaml", "com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.10.1")
            bundle("jackson", listOf("jacksonKotlin", "jacksonJsr"))
            bundle("jsonSchema", listOf("jsonSchemaValidator", "jacksonKotlin", "jacksonYaml"))

            version("exposed", "0.51.0")
            library("exposedCore", "org.jetbrains.exposed", "exposed-core").versionRef("exposed")
            library("exposedJdbc", "org.jetbrains.exposed", "exposed-jdbc").versionRef("exposed")
            library("exposedJavaTime", "org.jetbrains.exposed", "exposed-java-time").versionRef("exposed")
            library("exposedJson", "org.jetbrains.exposed", "exposed-json").versionRef("exposed")
            bundle("exposed", listOf("exposedCore", "exposedJdbc", "exposedJavaTime", "exposedJson"))

            version("http4k", "5.13.9.0")
            library("http4kCore", "org.http4k", "http4k-core").versionRef("http4k")
            library("http4kServerUndertow", "org.http4k", "http4k-server-undertow").versionRef("http4k")
            library("http4kClientApache", "org.http4k", "http4k-client-apache").versionRef("http4k")
            library("http4kFormatJackson", "org.http4k", "http4k-format-jackson").versionRef("http4k")
            bundle("http4k", listOf("http4kCore", "http4kServerUndertow", "http4kClientApache", "http4kFormatJackson"))
        }
    }
}
