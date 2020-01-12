package pl.exsio.querydsl.entityql.examples.service.mixed;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.entity.Book;
import pl.exsio.querydsl.entityql.examples.entity.Group;
import pl.exsio.querydsl.entityql.examples.entity.OrderItem;
import pl.exsio.querydsl.entityql.examples.entity.generated.*;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static pl.exsio.querydsl.entityql.EntityQL.qEntity;

@Service
public class QMixedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QMixedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityONJoin() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        Q<OrderItem> orderItem = qEntity(OrderItem.class);

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(book)
                .innerJoin(orderItem).on(orderItem.longNumber("bookId").eq(book.id))
                .innerJoin(order).on(orderItem.longNumber("orderId").eq(order.id))
                .where(order.id.eq(1L))
                .fetch();

        System.out.println(books);
    }

    private void getAllRowsFromAnEntityBasedOnFKJoin() {

        Q<Book> book = QBook.INSTANCE.dynamic();
        QOrder order = QOrder.INSTANCE;
        Q<OrderItem> orderItem = QOrderItem.INSTANCE.dynamic();

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
                .innerJoin(orderItem.joinColumn("book"), book)
                .innerJoin(orderItem.joinColumn("order"), order)
                .where(order.id.eq(2L))
                .fetch();

        System.out.println(books);
    }



    @Override
    public void run() {
        getAllRowsFromAnEntityONJoin();
        getAllRowsFromAnEntityBasedOnFKJoin();
    }
}
