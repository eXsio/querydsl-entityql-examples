package pl.exsio.querydsl.entityql.examples.jpa.example.generated;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.jpa.entity.UploadedFile;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUploadedFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QJPADmlGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QJPADmlGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void insertNewEntity() {
        QBook book = QBook.INSTANCE;
        queryFactory.insert(book)
                .set(book.id, 100L)
                .set(book.name, "newBook3")
                .set(book.price, BigDecimal.ONE)
                .execute();

    }

    private void insertNewEntityUsingSetMethod() {
        QBook book = QBook.INSTANCE;
        book.set(
                queryFactory.insert(book),
                book.id, 110L,
                book.name, "newBook4",
                book.price, BigDecimal.ONE)
                .execute();

    }

    private void updateExistingEntity() {

        QBook book = QBook.INSTANCE;

        queryFactory.update(book)
                .set(book.name, "updatedBook2")
                .set(book.price, BigDecimal.ONE)
                .where(book.id.eq(9L))
                .execute();
    }


    private void updateExistingEntityUsingSetMethod() {

        QBook book = QBook.INSTANCE;

        SQLUpdateClause update = queryFactory.update(book)
                .where(book.id.eq(9L));

        book.set(update,
                book.name, "updatedBook2",
                book.price, BigDecimal.ONE
        ).execute();
    }

    private void deleteExistingEntity() {

        QBook book = QBook.INSTANCE;

        queryFactory.delete(book)
                .where(book.id.eq(4L))
                .execute();

    }

    private void insertAndReadByteArray() {

        QUploadedFile file = QUploadedFile.INSTANCE;

        UUID id = UUID.randomUUID();
        int size = 10;
        byte[] data = new byte[size];
        IntStream.range(0, size).forEach(i -> data[i] = 2);

        queryFactory.insert(file)
                .set(file.id, id)
                .set(file.data, data)
                .execute();

        UploadedFile uploadedFile = queryFactory.select(
                constructor(UploadedFile.class, file.data, file.id))
                .from(file)
                .where(file.id.eq(id))
                .fetchOne();

        System.out.println(uploadedFile);
    }

    @Override
    @Transactional
    public void run() {
        insertNewEntity();
        insertNewEntityUsingSetMethod();
        updateExistingEntity();
        updateExistingEntityUsingSetMethod();
        deleteExistingEntity();
        insertAndReadByteArray();
    }
}
