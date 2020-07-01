package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.generated

import com.querydsl.sql.SQLQueryFactory
import com.querydsl.sql.dml.SQLUpdateClause
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.UploadedFile
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QUploadedFile

import java.math.BigDecimal
import java.util.UUID
import java.util.stream.IntStream

import static com.querydsl.core.types.Projections.constructor

@Service
class QJDBCDmlGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory

    QJDBCDmlGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory
    }

    private void insertNewEntity() {
        QBook book = QBook.INSTANCE
        queryFactory.insert(book)
                .set(book.id, 300L)
                .set(book.name, "newBook31")
                .set(book.price, BigDecimal.ONE)
                .execute()

    }

    private void insertNewEntityUsingSetMethod() {
        QBook book = QBook.INSTANCE
        book.set(
                queryFactory.insert(book),
                book.id, 3110L,
                book.name, "newBook44",
                book.price, BigDecimal.ONE)
                .execute()

    }

    private void updateExistingEntity() {

        QBook book = QBook.INSTANCE

        queryFactory.update(book)
                .set(book.name, "updatedBook223")
                .set(book.price, BigDecimal.ONE)
                .where(book.id.eq(9L))
                .execute()
    }


    private void updateExistingEntityUsingSetMethod() {

        QBook book = QBook.INSTANCE

        SQLUpdateClause update = queryFactory.update(book)
                .where(book.id.eq(9L))

        book.set(update,
                book.name, "updatedBook432",
                book.price, BigDecimal.ONE
        ).execute()
    }

    private void deleteExistingEntity() {

        QBook book = QBook.INSTANCE

        queryFactory.delete(book)
                .where(book.id.eq(4L))
                .execute()

    }

    private void insertAndReadByteArray() {

        QUploadedFile file = QUploadedFile.INSTANCE

        UUID id = UUID.randomUUID()
        int size = 10
        byte[] data = new byte[size]
        IntStream.range(0, size).forEach{i -> data[i] = 2}

        queryFactory.insert(file)
                .set(file.id, id)
                .set(file.data, data)
                .execute()

        UploadedFile uploadedFile = queryFactory.select(
                constructor(UploadedFile.class, file.data, file.id))
                .from(file)
                .where(file.id.eq(id))
                .fetchOne()

        System.out.println(uploadedFile)
    }

    @Override
    @Transactional
    void run() {
        insertNewEntity()
        insertNewEntityUsingSetMethod()
        updateExistingEntity()
        updateExistingEntityUsingSetMethod()
        deleteExistingEntity()
        insertAndReadByteArray()
    }
}
