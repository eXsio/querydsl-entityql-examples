package pl.exsio.querydsl.entityql.examples.service.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.entity.Book;
import pl.exsio.querydsl.entityql.examples.entity.UploadedFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QDmlDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QDmlDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void insertNewEntity() {
        Q<Book> book = EntityQL.qEntity(Book.class);
        queryFactory.insert(book)
                .set(book.longNumber("id"), 10L)
                .set(book.string("name"), "newBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .execute();

    }

    private void insertNewEntityUsingSetMethod() {
        Q<Book> book = EntityQL.qEntity(Book.class);
        book.set(
                queryFactory.insert(book),
                "id", 11L,
                "name", "newBook2",
                "price", BigDecimal.ONE)
                .execute();

    }

    private void updateExistingEntity() {

        Q<Book> book = EntityQL.qEntity(Book.class);

        queryFactory.update(book)
                .set(book.string("name"), "updatedBook")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .where(book.longNumber("id").eq(9L))
                .execute();
    }


    private void updateExistingEntityUsingSetMethod() {

        Q<Book> book = EntityQL.qEntity(Book.class);

        SQLUpdateClause update = queryFactory.update(book)
                .where(book.longNumber("id").eq(9L));

        book.set(update,
                "name", "updatedBook",
                "price", BigDecimal.ONE
        ).execute();
    }

    private void deleteExistingEntity() {

        Q<Book> book = EntityQL.qEntity(Book.class);

        queryFactory.delete(book)
                .where(book.longNumber("id").eq(4L))
                .execute();

    }

    private void insertAndReadByteArray() {

        Q<UploadedFile> file = EntityQL.qEntity(UploadedFile.class);

        UUID id = UUID.randomUUID();
        int size = 10;
        byte[] data = new byte[size];
        IntStream.range(0, size).forEach(i -> data[i] = 2);

        queryFactory.insert(file)
                .set(file.uuid("id"), id)
                .set(file.array("data"), data)
                .execute();

        UploadedFile uploadedFile = queryFactory.select(
                constructor(UploadedFile.class, file.<byte[]>array("data"), file.uuid("id")))
                .from(file)
                .where(file.uuid("id").eq(id))
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
