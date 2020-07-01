package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.dto
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.*

@Service
class KQJPAJoinDynamicExample(@Autowired var queryFactory: SQLQueryFactory) : Example {
    
    fun getAllRowsFromAnEntityBasedOnAColumnONJoin() {

        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


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

        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


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

        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


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

    
    fun getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName() {

        val group = qEntity(Group::class.java)
        val groupAdmin = qEntity(GroupAdmin::class.java)


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(group)
                .innerJoin(group.joinColumn<GroupAdmin>("admin"), groupAdmin)
                .where(groupAdmin.longNumber("id").eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause() {

        val group = qEntity(Group::class.java)
        val user = qEntity(User::class.java)
        val userGroup = qEntity(UserGroup::class.java)


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(userGroup)
                .innerJoin(group).on(userGroup.longNumber("groupId").eq(group.longNumber("id")))
                .innerJoin(user).on(userGroup.longNumber("userId").eq(user.longNumber("id")))
                .where(user.longNumber("id").eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin() {

        val group = qEntity(Group::class.java)
        val user = qEntity(User::class.java)
        val userGroup = qEntity(UserGroup::class.java)


        val groups = queryFactory.query()
                .select(
                        constructor(
                                Group::class.java,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(userGroup)
                .innerJoin(userGroup.joinColumn<Group>("group"), group)
                .innerJoin(userGroup.joinColumn<User<*>>("user"), user)
                .where(user.longNumber("id").eq(2L))
                .fetch()


        println(groups)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseFKJoin() {

        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(order)
                .innerJoin(order.inverseJoinColumn<OrderItem>("items"), orderItem)
                .innerJoin(orderItem.joinColumn<Book>("book"), book)
                .where(order.longNumber("id").eq(2L))
                .fetch()


       println(books)
    }

    
    fun getAllRowsFromAnEntityBasedOnAnInverseFKJoinAndReferencedColumnName() {

        val book = qEntity(Book::class.java)
        val order = qEntity(Order::class.java)
        val orderItem = qEntity(OrderItem::class.java)


        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(order)
                .innerJoin(order.inverseJoinColumn<OrderItem>("itemsReferenced"), orderItem)
                .innerJoin(orderItem.joinColumn<Book>("book"), book)
                .where(order.longNumber("id").eq(2L))
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
