package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "GROUPS")
class Group() {

    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    @ManyToMany
    @JoinTable(
            name = "USERS_GROUPS",
            joinColumns = [JoinColumn(name = "GROUP_ID")],
            inverseJoinColumns = [JoinColumn(name = "USER_ID")]
    )
    var users: Set<User<Any>>? = null

    @ManyToOne
    @JoinColumn(name = "ADMIN_NAME", nullable = false, referencedColumnName = "NAME")
    var admin: GroupAdmin? = null

    constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }


}
