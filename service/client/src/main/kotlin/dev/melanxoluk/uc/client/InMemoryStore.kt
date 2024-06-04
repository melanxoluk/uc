package dev.melanxoluk.uc.client

/**
 * Listener listen events and updates in memory store
 * Application reacts on requests and accessing config from store
 * On missing config calling exception
 * 
 * Q: How to achieve application readiness?
 * A: Make explicit subscription on configuration update, while constructing UcClient
 */
class InMemoryStore {
    fun get(configuration: String, namedVersion: String?, )
}
