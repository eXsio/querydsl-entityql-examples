package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEMS", uniqueConstraints = [
        @UniqueConstraint(name = "unique_book_for_order",
                columnNames = ["BOOK_ID", "ITEM_ORDER_ID"])
])
class OrderItem implements Serializable {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue
    private Long id

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false)
    private Order order

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false, insertable = false, updatable = false, referencedColumnName = "ORDER_ID")
    private Order orderReferenced

    @Column(name = "QTY", nullable = false)
    private Long quantity

    OrderItem() {
    }

    OrderItem(Long id, Book book, Order order, Long quantity) {
        this.id = id
        this.book = book
        this.order = order
        this.quantity = quantity
    }

    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    Book getBook() {
        book
    }

    void setBook(Book book) {
        this.book = book
    }

    Long getQuantity() {
        quantity
    }

    void setQuantity(Long quantity) {
        this.quantity = quantity
    }

    Order getShoppingOrder() {
        order
    }

    void setShoppingOrder(Order order) {
        this.order = order
    }

}
