package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class GroupAdmin {

    @Id
    @Column("GA_ID")
    private final Long id
    private final String name

    GroupAdmin(String name) {
        this.name = name
    }

    Long getId() {
        id
    }

    String getName() {
        name
    }
}
