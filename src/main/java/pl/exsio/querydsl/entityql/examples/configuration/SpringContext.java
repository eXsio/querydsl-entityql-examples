package pl.exsio.querydsl.entityql.examples.configuration;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.exsio.querydsl.entityql.config.EntityQlQueryFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringContext {

    @Bean
    static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    /**
     * Entity Manager is needed only for creating and populating H2 database.
     * For normal use cases its presence is not required.
     */
    @Bean
    static LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("pl.exsio.querydsl.entityql");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.hbm2ddl.import_files", "data.sql");
        em.setJpaProperties(properties);

        return em;
    }

    /**
     * Regular Spring JDBC Transaction Manager
     */
    @Bean
    static PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SQLTemplates implementation that matches your Database
     */
    @Bean
    SQLTemplates sqlTemplates() {
        return new H2Templates();
    }

    /**
     * SQLQueryFactory - main entry point to build and execute SQL queries
     */
    @Bean
    static SQLQueryFactory queryFactory(DataSource dataSource, SQLTemplates sqlTemplates) {
        return new EntityQlQueryFactory(
                new com.querydsl.sql.Configuration(sqlTemplates),
                dataSource, "pl.exsio.querydsl.entityql.examples");
    }

}
