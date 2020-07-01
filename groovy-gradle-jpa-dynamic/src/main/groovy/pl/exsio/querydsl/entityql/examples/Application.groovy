package pl.exsio.querydsl.entityql.examples

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("pl.exsio.querydsl.entityql.examples")
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Bean
    CommandLineRunner commandLineRunner(List<Example> examples) {
        {args -> examples.forEach{example ->
            System.out.println("Running Example: "+example.getClass().getName())
            example.run()
        }}
    }
}
