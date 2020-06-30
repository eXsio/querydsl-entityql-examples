package pl.exsio.querydsl.entityql.examples.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*

import javax.sql.DataSource

@Configuration
open class H2SchemaCreationConfiguration {

    /**
     * Entity Manager is needed only for creating and populating H2 database.
     * For normal (production) use cases its presence is not required.
     */
    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()

        em.setDataSource(dataSource)
        em.setPackagesToScan("pl.exsio.querydsl.entityql")
        em.setJpaVendorAdapter(HibernateJpaVendorAdapter())

        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create")
        em.setJpaProperties(properties)

        return em
    }
}
