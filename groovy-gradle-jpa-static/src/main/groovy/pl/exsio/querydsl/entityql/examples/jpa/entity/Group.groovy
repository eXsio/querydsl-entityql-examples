package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "GROUPS")
class Group {

    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue
    private Long id

    @Column(name = "NAME")
    private String name

    @ManyToMany
    @JoinTable(
            name = "USERS_GROUPS",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users

    @ManyToOne
    @JoinColumn(name = "ADMIN_NAME", nullable = false, referencedColumnName = "NAME")
    private GroupAdmin admin

    Group() {
    }

    Group(Long id, String name) {
        this.id = id
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

    GroupAdmin getAdmin() {
        admin
    }

    void setAdmin(GroupAdmin admin) {
        this.admin = admin
    }
}
