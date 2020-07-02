package pl.exsio.querydsl.entityql.examples.jpa.entity


import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk
import javax.persistence.*

@Entity
@Table(name = "SINGULAR_PK")
class SingularPk() {

    @Id
    @Column(name = "ID_1")
    var id1: Long? = null

    @Column(name = "ID_2")
    var id2: String? = null

    @Column(name = "DESC")
    var desc: String? = null

    @OneToMany(mappedBy = "singularPk")
    var compositeFks: List<CompositeFk>? = null

    constructor(id1: Long, id2: String, desc: String) : this() {
        this.id1 = id1
        this.id2 = id2
        this.desc = desc
    }

}
