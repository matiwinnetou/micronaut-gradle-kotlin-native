package example.micronaut

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent
import jakarta.inject.Singleton

@Singleton
class AppRunner(
    private val greetingService: GreetingService

) : ApplicationEventListener<ApplicationStartupEvent> {

    override fun onApplicationEvent(event: ApplicationStartupEvent) {
        greetingService.greet()
    }

}
