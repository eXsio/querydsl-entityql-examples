package pl.exsio.querydsl.entityql.examples.jpa.example.generated

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
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUser
import java.util.*

@Service
class KQJPASimpleSelectGeneratedExample(@Autowired var queryFactory: SQLQueryFactory) : Example {


    fun getAllRowsFromAnEntity() {
        // given:
        val book = QBook.instance
        val books = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(book).fetch()


        println(books)
    }


    fun getOneRowFromAnEntity() {
        // given:
        val book = QBook.instance


        val p = queryFactory.query()
                .select(
                        constructor(
                                Book::class.java,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .where(book.id.eq(1L))
                .from(book).fetchOne()


        println(p)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumStringfilter() {
        // given:
        val user = QUser.instance


        val userName = queryFactory.query()
                .select(user.name)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(userName)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumOrdinalfilter() {
        // given:
        val user = QUser.instance


        val userName = queryFactory.query()
                .select(user.name)
                .where(user.typeOrd.eq(UserTypeByOrdinal.ADMIN))
                .from(user).fetchOne()


        println(userName)
    }


    fun getGenericFields() {
        // given:
        val user = QUser.instance


        val createdBy = queryFactory.query()
                .select(user.createdBy)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(createdBy)
    }


    fun getUnknownFields() {
        // given:
        val user = QUser.instance


        val createdBy = queryFactory.query()
                .select(user.createdBy)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(createdBy)
    }


    fun getEnumFields() {

        val user = QUser.instance


        val type = queryFactory.query()
                .select(user.typeStr)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()


        println(type)
    }


    fun getBooleanFields() {

        val user = QUser.instance


        val enabled : Boolean? = queryFactory.query()
                .select(user.enabled)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
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
