package pl.exsio.querydsl.entityql.examples.jpa.entity


import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
import pl.exsio.querydsl.entityql.examples.jpa.entity.Order
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEMS", uniqueConstraints = [
    UniqueConstraint(name = "junique_book_for_order",
            columnNames = ["BOOK_ID", "ITEM_ORDER_ID"])
])
class OrderItem() : Serializable {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    var book: Book? = null

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false)
    var order: Order? = null

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false, insertable = false, updatable = false, referencedColumnName = "ORDER_ID")
    var orderReferenced: Order? = null

    @Column(name = "QTY", nullable = false)
    var quantity: Long? = null

    constructor(id: Long, quantity: Long) : this() {
        this.id = id
        this.quantity = quantity
    }
}
