package pl.exsio.querydsl.entityql.examples

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@SpringBootApplication
@Configuration
@ComponentScan("pl.exsio.querydsl.entityql.examples")
open class Application {

    @Bean
    open fun commandLineRunner(examples: List<Example>): CommandLineRunner {
        return object : CommandLineRunner {
            override fun run(vararg args: String?) {
                examples.forEach { example ->
                    println("Running Example: " + example::class.qualifiedName)
                    example.run()
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args);
}
