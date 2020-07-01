package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.dto
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.*
import pl.exsio.querydsl.entityql.kotlin.spring_data_jdbc.entity.UpperCaseWithUnderscoresNamingStrategy

@Service
class KQSpringDataJDBCJoinDynamicExample(@Autowired val queryFactory: SQLQueryFactory) : Example {

    val scanner = SpringDataJdbcQEntityScanner(UpperCaseWithUnderscoresNamingStrategy())
    
    fun getAllRowsFromAnEntityBasedOnAColumnONJoin() {

        val book = qEntity(Book::class.java, scanner)
        val order = qEntity(Order::class.java, scanner)
        val orderItem = qEntity(OrderItem::class.java, scanner)


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(book)
                .innerJoin(orderItem).on(orderItem.longNumber("bookId").eq(book.longNumber("id")))
                .innerJoin(order).on(orderItem.longNumber("orderId").eq(order.longNumber("id")))
                .where(order.longNumber("id").eq(1L))
                .fetch()


        println(books)
    }

    
    fun getAllRowsUsingListOfColumnNames() {

        val book = qEntity(Book::class.java, scanner)
        val order = qEntity(Order::class.java, scanner)
        val orderItem = qEntity(OrderItem::class.java, scanner)


        val books = queryFactory.query()
                .select(
                        dto(Book::class.java, book.columns("id", "name", "desc", "price"))
                )
                .from(book)
                .innerJoin(orderItem).on(orderItem.longNumber("bookId").eq(book.longNumber("id")))
                .innerJoin(order).on(orderItem.longNumber("orderId").eq(order.longNumber("id")))
                .where(order.longNumber("id").eq(1L))
                .fetch()


        println(books)
    }

    
    fun getAllRowsFromAnEntityBasedOnFKJoin() {

        val book = qEntity(Book::class.java, scanner)
        val order = qEntity(Order::class.java, scanner)
        val orderItem = qEntity(OrderItem::class.java, scanner)


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(orderItem)
                .innerJoin(orderItem.joinColumn<Book>("book"), book)
                .innerJoin(orderItem.joinColumn<Order>("order"), order)
                .where(order.longNumber("id").eq(2L))
                .fetch()


        println(books)
    }

    override fun run() {
        getAllRowsFromAnEntityBasedOnAColumnONJoin()
        getAllRowsFromAnEntityBasedOnFKJoin()
        getAllRowsUsingListOfColumnNames()
    }

}
