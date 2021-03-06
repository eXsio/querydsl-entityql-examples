package pl.exsio.querydsl.entityql.examples.jpa.example.generated

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.dto
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.*
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.*

@Service
class KQJPAJoinGeneratedExample(@Autowired val queryFactory: SQLQueryFactory) : Example {
    
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

    
    fun getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName() {

        val group = QGroup.instance
        val groupAdmin = QGroupAdmin.instance


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.id,
                                group.name
                        ))
                .from(group)
                .innerJoin(group.admin, groupAdmin)
                .where(groupAdmin.id.eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause() {

        val group = QGroup.instance
        val user = QUser.instance
        val userGroup = QUserGroup.instance


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.id,
                                group.name
                        ))
                .from(userGroup)
                .innerJoin(group).on(userGroup.groupId.eq(group.id))
                .innerJoin(user).on(userGroup.userId.eq(user.id))
                .where(user.id.eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin() {

        val group = QGroup.instance
        val user = QUser.instance
        val userGroup = QUserGroup.instance


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.id,
                                group.name
                        ))
                .from(userGroup)
                .innerJoin(userGroup.group, group)
                .innerJoin(userGroup.user, user)
                .where(user.id.eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseFKJoin() {

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
                .from(order)
                .innerJoin(order.items, orderItem)
                .innerJoin(orderItem.book, book)
                .where(order.id.eq(2L))
                .fetch()


       println(books)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseFKJoinAndReferencedColumnName() {

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
                .from(order)
                .innerJoin(order.itemsReferenced, orderItem)
                .innerJoin(orderItem.book, book)
                .where(order.id.eq(2L))
                .fetch()


        println(books)
    }

    override fun run() {
        getAllRowsFromAnEntityBasedOnAColumnONJoin()
        getAllRowsFromAnEntityBasedOnAnInverseFKJoin()
        getAllRowsFromAnEntityBasedOnAnInverseFKJoinAndReferencedColumnName()
        getAllRowsFromAnEntityBasedOnFKJoin()
        getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName()
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin()
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause()
        getAllRowsUsingListOfColumnNames()
    }

}
