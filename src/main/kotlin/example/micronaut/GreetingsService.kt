package example.micronaut

import jakarta.inject.Singleton

@Singleton
class GreetingService {
    fun greet() {
        println("Hello from a Micronaut CLI app in Kotlin! 👋")
    }
}
