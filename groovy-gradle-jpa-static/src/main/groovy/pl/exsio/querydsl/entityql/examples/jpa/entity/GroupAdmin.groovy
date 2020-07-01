package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "GROUP_ADMINS")
class GroupAdmin {

    @Id
    @Column(name = "GA_ID")
    @GeneratedValue
    private Long id

    @Column(name = "NAME")
    private String name


    GroupAdmin() {
    }

    GroupAdmin(String name) {
        this.name = name
    }

    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }
}
