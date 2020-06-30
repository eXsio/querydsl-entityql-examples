package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

import javax.annotation.Nonnull

class OrderItem implements Serializable {

    @Id
    @Column("ORDER_ITEM_ID")
    private final Long id
    @Nonnull
    @Column("BOOK_ID")
    private final Book book
    @Nonnull
    @Column("ITEM_ORDER_ID")
    private final Order order
    @Nonnull
    @Column("QTY")
    private final Long quantity

    OrderItem(Long id, Book book, Order order, Long quantity) {
        this.id = id
        this.book = book
        this.order = order
        this.quantity = quantity
    }

    Long getId() {
        id
    }

    Book getBook() {
        book
    }

    Long getQuantity() {
        quantity
    }

    Order getShoppingOrder() {
        order
    }
}
