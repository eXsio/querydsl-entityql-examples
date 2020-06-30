package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.generated

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QUser

@Service
class KQSpringDataJDBCSimpleSelectGeneratedExample(@Autowired var queryFactory: SQLQueryFactory) : Example {


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

        //then:
        println(books)
    }


    fun getOneRowFromAnEntity() {
        // given:
        val book = QBook.instance

        //when:
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

        //then:
        println(p)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumStringfilter() {
        // given:
        val user = QUser.instance

        //when:
        val userName = queryFactory.query()
                .select(user.name)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()

        //then:
        println(userName)
    }


    fun getAllRowsFromAnEntityBasedOnAnEnumOrdinalfilter() {
        // given:
        val user = QUser.instance

        //when:
        val userName = queryFactory.query()
                .select(user.name)
                .where(user.typeOrd.eq(UserTypeByOrdinal.ADMIN))
                .from(user).fetchOne()

        //then:
        println(userName)
    }


    fun getGenericFields() {
        // given:
        val user = QUser.instance

        //when:
        val createdBy = queryFactory.query()
                .select(user.createdBy)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()

        //then:
        println(createdBy)
    }


    fun getUnknownFields() {
        // given:
        val user = QUser.instance

        //when:
        val createdBy = queryFactory.query()
                .select(user.createdBy)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()

        //then:
        println(createdBy)
    }


    fun getEnumFields() {
        //given:
        val user = QUser.instance

        //when:
        val type = queryFactory.query()
                .select(user.typeStr)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()

        //then:
        println(type)
    }


    fun getBooleanFields() {
        //given:
        val user = QUser.instance

        //when:
        val enabled : Boolean? = queryFactory.query()
                .select(user.enabled)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne()

        //then:

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
