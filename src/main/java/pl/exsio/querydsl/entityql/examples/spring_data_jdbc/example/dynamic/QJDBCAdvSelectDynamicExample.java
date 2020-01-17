package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.dynamic;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.entity.scanner.QEntityScanner;
import pl.exsio.querydsl.entityql.entity.scanner.SpringDataJdbcQEntityScanner;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.dto.OrderItemBookCountDto;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Order;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.OrderItem;
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.User;
import pl.exsio.querydsl.entityql.jdbc.UpperCaseWithUnderscoresNamingStrategy;

import java.math.BigDecimal;
import java.util.List;

import static com.querydsl.sql.SQLExpressions.count;

@Service
class QJDBCAdvSelectDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    private final QEntityScanner scanner = new SpringDataJdbcQEntityScanner(new UpperCaseWithUnderscoresNamingStrategy());

    public QJDBCAdvSelectDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void useAggregateFunctions() {

        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        List<OrderItemBookCountDto> result = queryFactory
                .select(
                        Projections.constructor(
                                OrderItemBookCountDto.class,
                                orderItem.longNumber("orderId"),
                                SQLExpressions.count(orderItem.longNumber("bookId"))))
                .from(orderItem)
                .groupBy(orderItem.longNumber("orderId"))
                .orderBy(orderItem.longNumber("orderId").asc())
                .fetch();

        System.out.println(result);
    }

    private void useSubQueries() {

        Q<User> user = EntityQL.qEntity(User.class, scanner);
        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Q<Order> order = EntityQL.qEntity(Order.class, scanner);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        List<String> result = queryFactory.select(user.string("name"))
                .from(user)
                .where(user.longNumber("id").in(
                        SQLExpressions.select(order.longNumber("userId"))
                                .from(orderItem)
                                .innerJoin(orderItem.<Book>joinColumn("book"), book)
                                .innerJoin(orderItem.<Order>joinColumn("order"), order)
                                .where(book.decimalNumber("price").gt(new BigDecimal("80")))
                )).fetch();

        System.out.println(result);

    }

    private void useNestedSelects() {

        Q<Book> book = EntityQL.qEntity(Book.class, scanner);
        Q<Order> order = EntityQL.qEntity(Order.class, scanner);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class, scanner);

        Long result = queryFactory.select(count())
                .from(
                        SQLExpressions.select(order.longNumber("userId"))
                                .from(orderItem)
                                .innerJoin(orderItem.<Book>joinColumn("book"), book)
                                .innerJoin(orderItem.<Order>joinColumn("order"), order)
                                .where(book.decimalNumber("price").gt(new BigDecimal("80")))
                ).fetchOne();

        System.out.println(result);
    }

    @Override
    public void run() {
        useAggregateFunctions();
        useSubQueries();
        useNestedSelects();
    }
}
