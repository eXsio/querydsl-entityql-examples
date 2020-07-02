package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.generated

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.dto
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.*
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.*

@Service
class KQSpringDataJDBCJoinGeneratedExample(@Autowired val queryFactory: SQLQueryFactory) : Example {
    
    fun getAllRowsFromAnEntityBasedOnAColumnONJoin() {

        val book = QBook.instance
        val order = QOrder.instance
        val orderItem = QOrderItem.instance


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(book)
                .innerJoin(orderItem).on(orderItem.bookId.eq(book.id))
                .innerJoin(order).on(orderItem.orderId.eq(order.id))
                .where(order.id.eq(1L))
                .fetch()


        println(books)
    }

    
    fun getAllRowsUsingListOfColumnNames() {

        val book = QBook.instance
        val order = QOrder.instance
        val orderItem = QOrderItem.instance


        val books = queryFactory.query()
                .select(
                        dto(Book::class.java, listOf(book.id, book.name, book.desc, book.price))
                )
                .from(book)
                .innerJoin(orderItem).on(orderItem.bookId.eq(book.id))
                .innerJoin(order).on(orderItem.orderId.eq(order.id))
                .where(order.id.eq(1L))
                .fetch()


        println(books)
    }

    
    fun getAllRowsFromAnEntityBasedOnFKJoin() {

        val book = QBook.instance
        val order = QOrder.instance
        val orderItem = QOrderItem.instance


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(orderItem)
                .innerJoin(orderItem.book, book)
                .innerJoin(orderItem.order, order)
                .where(order.id.eq(2L))
                .fetch()


        println(books)
    }

    override fun run() {
        getAllRowsFromAnEntityBasedOnAColumnONJoin()
        getAllRowsFromAnEntityBasedOnFKJoin()
        getAllRowsUsingListOfColumnNames()
    }

}
