package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
import pl.exsio.querydsl.entityql.examples.jpa.entity.User
import java.util.*

@Service
class KQJPASimpleSelectDynamicExample(@Autowired val queryFactory: SQLQueryFactory) : Example {


    fun getAllRowsFromAnEntity() {
        // given:
        val book = qEntity(Book::class.java)
        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(book).fetch()


        println(books)
    }


    fun getOneRowFromAnEntity() {
        // given:
        val book = qEntity(Book::class.java)


        val p = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .where(book.longNumber("id").eq(1L))
                .from(book).fetchOne()


        println(p)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumStringfilter() {
        // given:
        val user = qEntity(User::class.java)


        val userName = queryFactory.query()
                .select(user.string("name"))
                .where(user.enumerated<UserTypeByName>("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(userName)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumOrdinalfilter() {
        // given:
        val user = qEntity(User::class.java)


        val userName = queryFactory.query()
                .select(user.string("name"))
                .where(user.enumerated<UserTypeByOrdinal>("typeOrd").eq(UserTypeByOrdinal.ADMIN))
                .from(user).fetchOne()


        println(userName)
    }


    fun getGenericFields() {
        // given:
        val user = qEntity(User::class.java)


        val createdBy = queryFactory.query()
                .select(user.column<String>("createdBy"))
                .where(user.enumerated<UserTypeByName>("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(createdBy)
    }


    fun getUnknownFields() {
        // given:
        val user = qEntity(User::class.java)


        val createdBy = queryFactory.query()
                .select(user.column<Date>("createdAt"))
                .where(user.enumerated<UserTypeByName>("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(createdBy)
    }


    fun getEnumFields() {

        val user = qEntity(User::class.java)


        val type = queryFactory.query()
                .select(user.enumerated<UserTypeByName>("typeStr"))
                .where(user.enumerated<UserTypeByName>("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(type)
    }


    fun getBooleanFields() {

        val user = qEntity(User::class.java)


        val enabled : Boolean? = queryFactory.query()
                .select(user.bool("enabled"))
                .where(user.enumerated<UserTypeByName>("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()



        println(enabled)
    }

    override fun run() {
        getAllRowsFromAnEntity()
        getAllRowsFromAnEntityBasedOnAnEnumOrdinalfilter()
        getAllRowsFromAnEntityBasedOnAnEnumStringfilter()
        getBooleanFields()
        getEnumFields()
        getGenericFields()
        getOneRowFromAnEntity()
        getUnknownFields()
    }
}
