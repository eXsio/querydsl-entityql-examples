package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "BOOKS")
class Book {

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue
    private Long id

    @Column(name = "NAME", unique = true)
    private String name


    @Column(name = "DESC", nullable = true, columnDefinition = "CLOB")
    private String desc

    @Column(name = "PRICE")
    private BigDecimal price

    Book() {
    }

    Book(Long id, String name, String desc, BigDecimal price) {
        this.id = id
        this.name = name
        this.desc = desc
        this.price = price
    }

    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }

    BigDecimal getPrice() {
        price
    }

    void setPrice(BigDecimal price) {
        this.price = price
    }

    String getDesc() {
        desc
    }

    void setDesc(String desc) {
        this.desc = desc
    }
}
