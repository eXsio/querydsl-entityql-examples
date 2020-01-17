package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.annotation.Nonnull;
import java.io.Serializable;

public class OrderItem implements Serializable {

    @Id
    @Column("ORDER_ITEM_ID")
    private final Long id;

    @Column("BOOK_ID")
    private final Book book;

    private final Order order;

    @Column("QTY")
    private final Long quantity;

    public OrderItem(Long id, Book book, Order order, Long quantity) {
        this.id = id;
        this.book = book;
        this.order = order;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }
}
