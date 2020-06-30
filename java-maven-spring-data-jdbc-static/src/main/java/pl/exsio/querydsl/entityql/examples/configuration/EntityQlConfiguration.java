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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.exsio.querydsl.entityql.config.EntityQlQueryFactory;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class EntityQlConfiguration {

    /**
     * Your Datasource
     */
    @Bean
    static DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("data.sql")
                .build();
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
        /*
         * EntityQlQueryFactory is preconfigured to work seamlessly with Spring's Transaction Management
         */
        return new EntityQlQueryFactory(new com.querydsl.sql.Configuration(sqlTemplates), dataSource)
                /*
                 * all Java Enums from the below package will be mapped to a String/Varchar column that
                 * represents a name() value of that Enum constant
                 */
                .registerEnumsByName("pl.exsio.querydsl.entityql.examples.enums.by_name")
                /*
                 * all Java Enums from the below package will be mapped to an Integer column that
                 * represents an ordinal() value of that Enum constant
                 */
                .registerEnumsByOrdinal("pl.exsio.querydsl.entityql.examples.enums.by_ordinal");
    }
}
