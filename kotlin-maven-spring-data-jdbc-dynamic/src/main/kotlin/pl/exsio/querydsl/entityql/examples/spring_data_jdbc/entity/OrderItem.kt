package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Order

class OrderItem(@Id @Column("ORDER_ITEM_ID") var id: Long,
                @Column("BOOK_ID") var book: Book,
                @Column("ITEM_ORDER_ID") var order: Order,
                @Column("QTY") var quantity: Long
)

