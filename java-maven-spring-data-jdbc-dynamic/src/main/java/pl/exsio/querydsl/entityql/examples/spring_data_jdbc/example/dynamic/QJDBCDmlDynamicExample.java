package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.entity.scanner.QEntityScanner;
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.configuration.UpperCaseWithUnderscoresNamingStrategy;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.UploadedFile;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QJDBCDmlDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    private final QEntityScanner scanner = new SpringDataJdbcQEntityScanner(new UpperCaseWithUnderscoresNamingStrategy());

    public QJDBCDmlDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void insertNewEntity() {
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        queryFactory.insert(book)
                .set(book.longNumber("id"), 200L)
                .set(book.string("name"), "newBook32")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .execute();

    }

    private void insertNewEntityUsingSetMethod() {
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        book.set(
                queryFactory.insert(book),
                "id", 210L,
                "name", "newBook43",
                "price", BigDecimal.ONE)
                .execute();

    }

    private void updateExistingEntity() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);

        queryFactory.update(book)
                .set(book.string("name"), "updatedBook24")
                .set(book.decimalNumber("price"), BigDecimal.ONE)
                .where(book.longNumber("id").eq(9L))
                .execute();
    }


    private void updateExistingEntityUsingSetMethod() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);

        SQLUpdateClause update = queryFactory.update(book)
                .where(book.longNumber("id").eq(9L));

        book.set(update,
                "name", "updatedBook35",
                "price", BigDecimal.ONE
        ).execute();
    }

    private void deleteExistingEntity() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);

        queryFactory.delete(book)
                .where(book.longNumber("id").eq(4L))
                .execute();

    }

    private void insertAndReadByteArray() {

        Q<UploadedFile> file = EntityQL.qEntity(UploadedFile.class, scanner);

        UUID id = UUID.randomUUID();
        int size = 10;
        byte[] data = new byte[size];
        IntStream.range(0, size).forEach(i -> data[i] = 2);

        queryFactory.insert(file)
                .set(file.uuid("id"), id)
                .set(file.array("data"), data)
                .execute();

        UploadedFile uploadedFile = queryFactory.select(
                constructor(UploadedFile.class, file.<byte[], Byte>array("data"), file.uuid("id")))
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
