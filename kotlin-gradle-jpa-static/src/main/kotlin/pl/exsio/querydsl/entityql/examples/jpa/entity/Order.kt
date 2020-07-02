package pl.exsio.querydsl.entityql.examples.jpa.entity


import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order() : Serializable {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue
    var id: Long? = null

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    var user: User<Any>? = null

    @OneToMany(mappedBy = "order")
    var items: List<OrderItem>? = null

    @OneToMany(mappedBy = "orderReferenced")
    var itemsReferenced: List<OrderItem>? = null


}
