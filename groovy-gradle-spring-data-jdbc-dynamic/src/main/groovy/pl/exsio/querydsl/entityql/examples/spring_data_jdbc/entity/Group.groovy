package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id

class Group {

    @Id
    private Long id
    private String name
    private GroupAdmin admin

    Group(Long id, String name) {
        this.id = id
        this.name = name
    }

    Long getId() {
        id
    }

    String getName() {
        name
    }

    GroupAdmin getAdmin() {
        admin
    }
}
