package pl.exsio.querydsl.entityql.examples.jpa.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLExpressions.count
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
import pl.exsio.querydsl.entityql.examples.jpa.entity.UploadedFile

import java.math.BigDecimal
import java.util.*
import javax.transaction.Transactional

@Service
open class KQJPADmlDynamicExample(@Autowired val queryFactory: SQLQueryFactory) : Example {

    
    fun insertNewEntity() {

        val book = qEntity(Book::class.java)


        queryFactory.insert(book)
                .set(book.longNumber("id"), 10L)
                .set(book.string("name"), "newBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .execute();


        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertNewEntityUsingSetMethod() {

        val book = qEntity(Book::class.java)


        book.set(
                queryFactory.insert(book),
                "id", 11L,
                "name", "newBook2",
                "price", BigDecimal.ONE)
                .execute()



        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun updateExistingEntity() {

        val book = qEntity(Book::class.java)


        queryFactory.update(book)
                .set(book.string("name"), "updatedBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .where(book.longNumber("id").eq(9L))
                .execute();


        println(queryFactory.query().select(count()).from(book)
                .where(book.string("name").eq("updatedBook")
                        .and(book.decimalNumber("price").eq(BigDecimal.ONE))
                        .and(book.longNumber("id").eq(9L)))
                .fetchOne())
    }

    
    fun updateExistingEntityUsingSetMethod() {

        val book = qEntity(Book::class.java)


        val update = queryFactory.update(book)
                .where(book.longNumber("id").eq(9L))

        book.set(update,
                "name", "updatedBook",
                "price", BigDecimal.ONE
        ).execute()


        println(queryFactory.query().select(count()).from(book)
                .where(book.string("name").eq("updatedBook")
                        .and(book.decimalNumber("price").eq(BigDecimal.ONE))
                        .and(book.longNumber("id").eq(9L)))
                .fetchOne())
    }

    
    fun deleteExistingEntity() {

        val book = qEntity(Book::class.java)


        queryFactory.delete(book)
                .where(book.longNumber("id").eq(4L))
                .execute();


        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertAndReadByteArray() {

        val file = qEntity(UploadedFile::class.java)

        val id = UUID.randomUUID()
        val data = "someData".toByteArray()


        queryFactory.insert(file)
                .set(file.uuid("id"), id)
                .set(file.array<ByteArray, Byte>("data"), data)
                .execute()

        //and:
        val uploadedFile = queryFactory.select(
                constructor(UploadedFile::class.java,
                        file.uuid("id"),
                        file.array<ByteArray, Byte>("data")
                ))
                .from(file)
                .where(file.uuid("id").eq(id))
                .fetchOne()


       println(uploadedFile)
    }

    @Transactional
    override open fun run() {
        insertNewEntity()
        insertNewEntityUsingSetMethod()
        updateExistingEntity()
        updateExistingEntityUsingSetMethod()
        deleteExistingEntity()
        insertAndReadByteArray()
    }
}
