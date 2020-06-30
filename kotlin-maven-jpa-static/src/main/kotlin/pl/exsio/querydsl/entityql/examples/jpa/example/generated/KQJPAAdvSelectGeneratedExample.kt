package pl.exsio.querydsl.entityql.examples.jpa.example.generated


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
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QOrder
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QOrderItem
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUser
import java.math.BigDecimal


@Service
class KQJPAAdvSelectGeneratedExample(@Autowired var queryFactory: SQLQueryFactory) : Example {

    
    fun useAggregateFunctions() {
        //given:
        val orderItem = QOrderItem.instance

        //when:

        val result = queryFactory
                .select(
                        constructor(
                                OrderItemBookCountDto::class.java,
                                orderItem.orderId,
                                count(orderItem.bookId)))
                .from(orderItem)
                .groupBy(orderItem.orderId)
                .orderBy(orderItem.orderId.asc())
                .fetch();

        //then:
        println(result)
    }
    
    fun useSubQueries() {
        //given:
        val user = QUser.instance
        val book = QBook.instance
        val order = QOrder.instance
        val orderItem = QOrderItem.instance

        //when:
        val result = queryFactory
                .select(user.name)
                .from(user)
                .where(user.id.`in`(
                        select(order.userId)
                                .from(orderItem)
                                .innerJoin(orderItem.book, book)
                                .innerJoin(orderItem.order, order)
                                .where(book.price.gt(BigDecimal("80")))
                )).fetch()

        //then:
        println(result)

    }

    fun useNestedSelects() {
        // given:
        val book = QBook.instance
        val order = QOrder.instance
        val orderItem = QOrderItem.instance

        //when:
        val result = queryFactory
                .select(count(Wildcard.all))
                .from(
                        select(order.userId)
                                .from(orderItem)
                                .innerJoin(orderItem.book, book)
                                .innerJoin(orderItem.order, order)
                                .where(book.price.gt(BigDecimal("80")))
                ).fetchOne()

        //then:
        println(result)
    }

    override fun run() {
        useAggregateFunctions()
        useNestedSelects()
        useSubQueries()
    }
}
