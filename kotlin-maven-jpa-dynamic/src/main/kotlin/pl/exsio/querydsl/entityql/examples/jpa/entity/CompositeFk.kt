package pl.exsio.querydsl.entityql.examples.jpa.entity


import javax.persistence.*

@Entity
@Table(name = "COMPOSITE_FK")
class CompositeFk() {

    @Id
    @Column(name = "ID")
    var id: Long? = null

    @ManyToOne
    @JoinColumns(value = [
        JoinColumn(name = "CPK_ID_1", nullable = false, referencedColumnName = "ID_1"),
        JoinColumn(name = "CPK_ID_2", nullable = false, referencedColumnName = "ID_2")
    ])
    var compositePk: CompositePk? = null

    @ManyToOne
    @JoinColumns(value = [
        JoinColumn(name = "SPK_ID_1", nullable = false, referencedColumnName = "ID_1"),
        JoinColumn(name = "SPK_ID_2", nullable = false, referencedColumnName = "ID_2")
    ])
    var singularPk: SingularPk? = null

    @Column(name = "DESC")
    var desc: String? = null
}
