package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class Book {

    @Id
    @Column("BOOK_ID")
    private final Long id
    private final String name
    private final String desc
    private final BigDecimal price

    Book(Long id, String name, String desc, BigDecimal price) {
        this.id = id
        this.name = name
        this.desc = desc
        this.price = price
    }

    Long getId() {
        id
    }

    String getName() {
        name
    }

    BigDecimal getPrice() {
        price
    }

    String getDesc() {
        desc
    }
}
