package pl.exsio.querydsl.entityql.examples.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter

import javax.sql.DataSource

@Configuration
class H2SchemaCreationConfiguration {

    /**
     * Entity Manager is needed only for creating and populating H2 database.
     * For normal (production) use cases its presence is not required.
     */
    @Bean
    static LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean()

        em.setDataSource(dataSource)
        em.setPackagesToScan("pl.exsio.querydsl.entityql")
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter())

        Properties properties = new Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create")
        properties.setProperty("hibernate.hbm2ddl.import_files", "data.sql")
        em.setJpaProperties(properties)

        em
    }
}
