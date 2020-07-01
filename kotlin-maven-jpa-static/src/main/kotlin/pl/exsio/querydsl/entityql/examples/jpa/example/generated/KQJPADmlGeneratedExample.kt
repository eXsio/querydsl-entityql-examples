package pl.exsio.querydsl.entityql.examples.jpa.example.generated

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLExpressions.count
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
import pl.exsio.querydsl.entityql.examples.jpa.entity.UploadedFile
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUploadedFile

import java.math.BigDecimal
import java.util.*
import javax.transaction.Transactional

@Service
open class KQJPADmlGeneratedExample(@Autowired var queryFactory: SQLQueryFactory) : Example {

    
    fun insertNewEntity() {

        val book = QBook.instance


        queryFactory.insert(book)
                .set(book.id, 10L)
                .set(book.name, "newBook")
                .set(book.price, BigDecimal.ONE)
                .execute();


        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertNewEntityUsingSetMethod() {

        val book = QBook.instance


        book.set(
                queryFactory.insert(book),
                book.id, 11L,
                book.name, "newBook2",
                book.price, BigDecimal.ONE)
                .execute()



        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun updateExistingEntity() {

        val book = QBook.instance


        queryFactory.update(book)
                .set(book.name, "updatedBook")
                .set(book.price, BigDecimal.ONE)
                .where(book.id.eq(9L))
                .execute();


        println(queryFactory.query().select(count()).from(book)
                .where(book.name.eq("updatedBook")
                        .and(book.price.eq(BigDecimal.ONE))
                        .and(book.id.eq(9L)))
                .fetchOne())
    }

    
    fun updateExistingEntityUsingSetMethod() {

        val book = QBook.instance


        val update = queryFactory.update(book)
                .where(book.longNumber("id").eq(9L))

        book.set(update,
                book.name, "updatedBook",
                book.price, BigDecimal.ONE
        ).execute()


        println(queryFactory.query().select(count()).from(book)
                .where(book.name.eq("updatedBook")
                        .and(book.price.eq(BigDecimal.ONE))
                        .and(book.id.eq(9L)))
                .fetchOne())
    }

    
    fun deleteExistingEntity() {

        val book = QBook.instance


        queryFactory.delete(book)
                .where(book.id.eq(4L))
                .execute();


        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertAndReadByteArray() {

        val file = QUploadedFile.instance

        val id = UUID.randomUUID()
        val data = "someData".toByteArray()


        queryFactory.insert(file)
                .set(file.id, id)
                .set(file.data, data)
                .execute()

        //and:
        val uploadedFile = queryFactory.select(
                constructor(UploadedFile::class.java,
                        file.id,
                        file.data
                ))
                .from(file)
                .where(file.id.eq(id))
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
