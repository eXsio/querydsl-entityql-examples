package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic

import com.querydsl.core.types.Projections.constructor
import com.querydsl.sql.SQLExpressions.count
import com.querydsl.sql.SQLQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import pl.exsio.querydsl.entityql.EntityQL.qEntity
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.UploadedFile
import pl.exsio.querydsl.entityql.kotlin.spring_data_jdbc.entity.UpperCaseWithUnderscoresNamingStrategy

import java.math.BigDecimal
import java.util.*

@Service
open class KQSpringDataJDBCDmlDynamicExample(@Autowired var queryFactory: SQLQueryFactory) : Example {

    var scanner = SpringDataJdbcQEntityScanner(UpperCaseWithUnderscoresNamingStrategy())
    
    fun insertNewEntity() {
        //given:
        val book = qEntity(Book::class.java, scanner)

        //when:
        queryFactory.insert(book)
                .set(book.longNumber("id"), 10L)
                .set(book.string("name"), "newBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .execute();

        //then:
        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertNewEntityUsingSetMethod() {
        //given:
        val book = qEntity(Book::class.java, scanner)

        //when:
        book.set(
                queryFactory.insert(book),
                "id", 11L,
                "name", "newBook2",
                "price", BigDecimal.ONE)
                .execute()


        //then:
        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun updateExistingEntity() {
        //given:
        val book = qEntity(Book::class.java, scanner)

        //when:
        queryFactory.update(book)
                .set(book.string("name"), "updatedBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .where(book.longNumber("id").eq(9L))
                .execute();

        //then:
        println(queryFactory.query().select(count()).from(book)
                .where(book.string("name").eq("updatedBook")
                        .and(book.decimalNumber("price").eq(BigDecimal.ONE))
                        .and(book.longNumber("id").eq(9L)))
                .fetchOne())
    }

    
    fun updateExistingEntityUsingSetMethod() {
        //given:
        val book = qEntity(Book::class.java, scanner)

        //when:
        val update = queryFactory.update(book)
                .where(book.longNumber("id").eq(9L))

        book.set(update,
                "name", "updatedBook",
                "price", BigDecimal.ONE
        ).execute()

        //then:
        println(queryFactory.query().select(count()).from(book)
                .where(book.string("name").eq("updatedBook")
                        .and(book.decimalNumber("price").eq(BigDecimal.ONE))
                        .and(book.longNumber("id").eq(9L)))
                .fetchOne())
    }

    
    fun deleteExistingEntity() {
        //given:
        val book = qEntity(Book::class.java, scanner)

        //when:
        queryFactory.delete(book)
                .where(book.longNumber("id").eq(4L))
                .execute();

        //then:
        println(queryFactory.query().select(count()).from(book).fetchOne())
    }

    
    fun insertAndReadByteArray() {
        //given:
        val file = qEntity(UploadedFile::class.java, scanner)

        val id = UUID.randomUUID()
        val data = "someData".toByteArray()

        //when:
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

        //then:
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
