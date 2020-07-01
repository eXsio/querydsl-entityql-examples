package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic


import com.querydsl.core.types.ExpressionUtils.count
import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.Wildcard
import com.querydsl.sql.SQLExpressions.select
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.dto.OrderItemBookCountDto
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
import pl.exsio.querydsl.entityql.examples.jpa.entity.Order
import pl.exsio.querydsl.entityql.examples.jpa.entity.OrderItem
import pl.exsio.querydsl.entityql.examples.jpa.entity.User
import java.math.BigDecimal


@Service
class KQJPAAdvSelectDynamicExample(@Autowired var queryFactory: SQLQueryFactory) : Example {

    
    fun useAggregateFunctions() {

        val orderItem = qEntity(OrderItem::class.java)



        val result = queryFactory
                .select(
                        constructor(
                                OrderItemBookCountDto::class.java,
                                orderItem.longNumber("orderId"),
                                count(orderItem.longNumber("bookId"))))
                .from(orderItem)
                .groupBy(orderItem.longNumber("orderId"))
                .orderBy(orderItem.longNumber("orderId").asc())
                .fetch();


        println(result)
    }
    
    fun useSubQueries() {

        val user = qEntity(User::class.java)
        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


        val result = queryFactory
                .select(user.string("name"))
                .from(user)
                .where(user.longNumber("id").`in`(
                        select(order.longNumber("userId"))
                                .from(orderItem)
                                .innerJoin(orderItem.joinColumn<Book>("book"), book)
                                .innerJoin(orderItem.joinColumn<Order>("order"), order)
                                .where(book.decimalNumber("price").gt(BigDecimal("80")))
                )).fetch()


        println(result)

    }

    fun useNestedSelects() {
        // given:
        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


        val result = queryFactory
                .select(count(Wildcard.all))
                .from(
                        select(order.longNumber("userId"))
                                .from(orderItem)
                                .innerJoin(orderItem.joinColumn<Book>("book"), book)
                                .innerJoin(orderItem.joinColumn<Order>("order"), order)
                                .where(book.decimalNumber("price").gt(BigDecimal("80")))
                ).fetchOne()


        println(result)
    }

    override fun run() {
        useAggregateFunctions()
        useNestedSelects()
        useSubQueries()
    }
}
