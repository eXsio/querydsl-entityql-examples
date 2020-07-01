package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.example.generated

import com.querydsl.sql.SQLQueryFactory
import org.springframework.stereotype.Service
import pl.exsio.querydsl.entityql.examples.Example
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QBook
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QOrder
import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated.QOrderItem

import java.util.List

import static com.querydsl.core.types.Projections.constructor

@Service
class QJDBCJoinGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory

    QJDBCJoinGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory
    }

    private void getAllRowsFromAnEntityONJoin() {

        QBook book = QBook.INSTANCE
        QOrder order = QOrder.INSTANCE
        QOrderItem orderItem = QOrderItem.INSTANCE

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
                .innerJoin(orderItem).on(orderItem.bookId.eq(book.id))
                .innerJoin(order).on(orderItem.orderId.eq(order.id))
                .where(order.id.eq(1L))
                .fetch()

        System.out.println(books)
    }

    private void getAllRowsFromAnEntityBasedOnFKJoin() {

        QBook book = QBook.INSTANCE
        QOrder order = QOrder.INSTANCE
        QOrderItem orderItem = QOrderItem.INSTANCE

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(orderItem)
                .innerJoin(orderItem.book, book)
                .innerJoin(orderItem.order, order)
                .where(order.id.eq(2L))
                .fetch()

        System.out.println(books)
    }

    @Override
    void run() {
        getAllRowsFromAnEntityONJoin()
        getAllRowsFromAnEntityBasedOnFKJoin()
    }
}
