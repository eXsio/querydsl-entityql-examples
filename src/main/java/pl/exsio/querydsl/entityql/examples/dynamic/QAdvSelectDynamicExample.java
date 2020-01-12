package pl.exsio.querydsl.entityql.examples.dynamic;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.dto.OrderItemBookCountDto;
import pl.exsio.querydsl.entityql.examples.entity.Book;
import pl.exsio.querydsl.entityql.examples.entity.Order;
import pl.exsio.querydsl.entityql.examples.entity.OrderItem;
import pl.exsio.querydsl.entityql.examples.entity.User;

import java.math.BigDecimal;
import java.util.List;

import static com.querydsl.sql.SQLExpressions.count;

@Service
class QAdvSelectDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QAdvSelectDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void useAggregateFunctions() {

        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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

        Q<User> user = EntityQL.qEntity(User.class);
        Q<Book> book = EntityQL.qEntity(Book.class);
        Q<Order> order = EntityQL.qEntity(Order.class);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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

        Q<Book> book = EntityQL.qEntity(Book.class);
        Q<Order> order = EntityQL.qEntity(Order.class);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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
