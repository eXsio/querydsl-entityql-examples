package pl.exsio.querydsl.entityql.examples.jpa.entity


import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "USERS")
class User<T>() {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    @OneToOne(mappedBy = "user")
    var order: Order? = null

    @Column(name = "TYPE_STR", nullable = false)
    @Enumerated(EnumType.STRING)
    var typeStr: UserTypeByName? = null

    @Column(name = "TYPE_ORD", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    var typeOrd: UserTypeByOrdinal? = null

    @Column(name = "CREATED_BY", columnDefinition = "varchar(15)")
    @org.hibernate.annotations.Type(type = "java.lang.String")
    var createdBy: T? = null

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    var createdAt: Date? = null

    @Column(name = "ENABLED", nullable = false)
    var enabled: Boolean? = null
}
