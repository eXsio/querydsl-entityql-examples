package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.entity.scanner.QEntityScanner;
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.configuration.UpperCaseWithUnderscoresNamingStrategy;
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName;
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.User;

import java.sql.Date;
import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QJDBCSimpleSelectDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    private final QEntityScanner scanner = new SpringDataJdbcQEntityScanner(new UpperCaseWithUnderscoresNamingStrategy());

    public QJDBCSimpleSelectDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void getAllRowsFromAnEntity() {
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(book).fetch();

        System.out.println(books);
    }

    public void getOneRowFromAnEntity() {
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Book result = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .where(book.longNumber("id").eq(1L))
                .from(book).fetchOne();

        System.out.println(result);
    }

    public void getAllRowsFromAnEntityBasedOnAnEnumStringFilter() {
        Q<User> user = EntityQL.qEntity(User.class, scanner);

        String userName = queryFactory.query()
                .select(user.string("name"))
                .where(user.<UserTypeByName> enumerated("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne();

        System.out.println(userName);
    }

    public void getAllRowsFromAnEntityBasedOnAnEnumOrdinalFilter() {

        Q<User> user = EntityQL.qEntity(User.class, scanner);


        String userName = queryFactory.query()
                .select(user.string("name"))
                .where(user.<UserTypeByOrdinal> enumerated("typeOrd").eq(UserTypeByOrdinal.ADMIN))
                .from(user).fetchOne();

        System.out.println(userName);
    }

    public void getGenericFields() {
        Q<User> user = EntityQL.qEntity(User.class, scanner);
        String createdBy = queryFactory.query()
                .select(user.<String> column("createdBy"))
                .where(user.<UserTypeByName> enumerated("typeStr").eq(UserTypeByName.ADMIN))
                .from(user).fetchOne();

        System.out.println(createdBy);
    }

    public void getUnknownFields() {
        Q<User> user = EntityQL.qEntity(User.class, scanner);

        Object createdBy = queryFactory.query()
                .select(user.<Date> column("createdAt"))
                .where(user.<UserTypeByName> enumerated("typeStr").eq(UserTypeByName.ADMIN))
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
