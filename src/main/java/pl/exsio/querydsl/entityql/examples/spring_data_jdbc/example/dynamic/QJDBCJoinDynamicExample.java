package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.entity.scanner.QEntityScanner;
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.*;
import pl.exsio.querydsl.entityql.jdbc.UpperCaseWithUnderscoresNamingStrategy;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static pl.exsio.querydsl.entityql.EntityQL.dto;

@Service
public class QJDBCJoinDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    private final QEntityScanner scanner = new SpringDataJdbcQEntityScanner(new UpperCaseWithUnderscoresNamingStrategy());

    public QJDBCJoinDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityONJoin() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Q<Order> order = EntityQL.qEntity(Order.class, scanner);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(book)
                .innerJoin(orderItem).on(orderItem.longNumber("bookId").eq(book.longNumber("id")))
                .innerJoin(order).on(orderItem.longNumber("orderId").eq(order.longNumber("id")))
                .where(order.longNumber("id").eq(1L))
                .fetch();

        System.out.println(books);
    }

    private void getAllRowsUsingListOfColumnNames() {
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Q<Order> order = EntityQL.qEntity(Order.class, scanner);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        List<Book> books = queryFactory.query()
                .select(dto(Book.class, book.columns("id", "name", "desc", "price")))
                .from(book)
                .innerJoin(orderItem).on(orderItem.longNumber("bookId").eq(book.longNumber("id")))
                .innerJoin(order).on(orderItem.longNumber("orderId").eq(order.longNumber("id")))
                .where(order.longNumber("id").eq(1L))
                .fetch();

        System.out.println(books);
    }


    private void getAllRowsFromAnEntityBasedOnFKJoin() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Q<Order> order = EntityQL.qEntity(Order.class, scanner);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.longNumber("id"),
                                book.string("name"),
                                book.string("desc"),
                                book.decimalNumber("price")
                        ))
                .from(orderItem)
                .innerJoin(orderItem.<Book>joinColumn("book"), book)
                .innerJoin(orderItem.<Order>joinColumn("order"), order)
                .where(order.longNumber("id").eq(2L))
                .fetch();

        System.out.println(books);
    }

    @Override
    public void run() {
        getAllRowsFromAnEntityONJoin();
        getAllRowsUsingListOfColumnNames();
        getAllRowsFromAnEntityBasedOnFKJoin();
    }
}
