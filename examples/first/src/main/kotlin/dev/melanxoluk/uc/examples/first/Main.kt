package dev.melanxoluk.uc.examples.first

import dev.melanxoluk.uc.client.UcClient

suspend fun main() {
    println(UcClient().fetch<Config>("first"))
}
