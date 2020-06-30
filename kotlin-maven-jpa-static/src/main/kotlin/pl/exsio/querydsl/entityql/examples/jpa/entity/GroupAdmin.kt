package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "GROUP_ADMINS")
class GroupAdmin() {

    @Id
    @Column(name = "GA_ID")
    @GeneratedValue
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    constructor(id: Long, name: String) : this() {
        this.id = id
        this.name = name
    }
}
