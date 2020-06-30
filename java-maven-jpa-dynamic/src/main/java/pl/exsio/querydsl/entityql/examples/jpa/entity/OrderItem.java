package pl.exsio.querydsl.entityql.examples.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_ITEMS", uniqueConstraints = {
        @UniqueConstraint(name = "unique_book_for_order",
                columnNames = {"BOOK_ID", "ITEM_ORDER_ID"})
})
public class OrderItem implements Serializable {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ITEM_ORDER_ID", nullable = false, insertable = false, updatable = false, referencedColumnName = "ORDER_ID")
    private Order orderReferenced;

    @Column(name = "QTY", nullable = false)
    private Long quantity;

    public OrderItem() {
    }

    public OrderItem(Long id, Book book, Order order, Long quantity) {
        this.id = id;
        this.book = book;
        this.order = order;
        this.quantity = quantity;
    }

}
