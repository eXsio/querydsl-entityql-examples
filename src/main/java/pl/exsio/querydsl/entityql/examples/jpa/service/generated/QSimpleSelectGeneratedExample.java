package pl.exsio.querydsl.entityql.examples.jpa.service.generated;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUser;
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName;
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal;

import java.util.Date;
import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QSimpleSelectGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QSimpleSelectGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntity() {
        QBook book = QBook.INSTANCE;
        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(book).fetch();

        System.out.println(books);
    }

    private void getOneRowFromAnEntity() {
        QBook book = QBook.INSTANCE;
        Book result = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .where(book.id.eq(1L))
                .from(book).fetchOne();

        System.out.println(result);
    }

    private void getAllRowsFromAnEntityBasedOnAnEnumStringFilter() {
        QUser user = QUser.INSTANCE;

        String userName = queryFactory.query()
                .select(user.name)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne();

        System.out.println(userName);
    }

    private void getAllRowsFromAnEntityBasedOnAnEnumOrdinalFilter() {

        QUser user = QUser.INSTANCE;
        String userName = queryFactory.query()
                .select(user.name)
                .where(user.typeOrd.eq(UserTypeByOrdinal.ADMIN))
                .from(user).fetchOne();

        System.out.println(userName);
    }

    private void getGenericFields() {
        QUser user = QUser.INSTANCE;
        Object createdBy = queryFactory.query()
                .select(user.createdBy)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne();

        System.out.println(createdBy);
    }

    private void getUnknownFields() {
        QUser user = QUser.INSTANCE;

        Date createdBy = queryFactory.query()
                .select(user.createdAt)
                .where(user.typeStr.eq(UserTypeByName.ADMIN))
                .from(user).fetchOne();

        System.out.println(createdBy);
    }


    @Override
    public void run() {
        getAllRowsFromAnEntity();
        getOneRowFromAnEntity();
        getAllRowsFromAnEntityBasedOnAnEnumStringFilter();
        getAllRowsFromAnEntityBasedOnAnEnumOrdinalFilter();
        getGenericFields();
        getUnknownFields();
    }
}
