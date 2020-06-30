package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

public class Book {

    @Id
    @Column("BOOK_ID")
    private final Long id;
    private final String name;
    private final String desc;
    private final BigDecimal price;

    public Book(Long id, String name, String desc, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
