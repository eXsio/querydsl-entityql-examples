package pl.exsio.querydsl.entityql.examples.jpa.service.generated;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.dto.OrderItemBookCountDto;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QBook;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QOrder;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QOrderItem;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.QUser;

import java.math.BigDecimal;
import java.util.List;

import static com.querydsl.sql.SQLExpressions.count;

@Service
class QAdvSelectGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QAdvSelectGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void useAggregateFunctions() {

        QOrderItem orderItem = QOrderItem.INSTANCE;

        List<OrderItemBookCountDto> result = queryFactory
                .select(
                        Projections.constructor(
                                OrderItemBookCountDto.class,
                                orderItem.orderId,
                                SQLExpressions.count(orderItem.bookId)))
                .from(orderItem)
                .groupBy(orderItem.orderId)
                .orderBy(orderItem.orderId.asc())
                .fetch();

        System.out.println(result);
    }

    private void useSubQueries() {

        QUser user = QUser.INSTANCE;
        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

        List<String> result = queryFactory.select(user.name)
                .from(user)
                .where(user.id.in(
                        SQLExpressions.select(order.userId)
                                .from(orderItem)
                                .innerJoin(orderItem.book, book)
                                .innerJoin(orderItem.order, order)
                                .where(book.price.gt(new BigDecimal("80")))
                )).fetch();

        System.out.println(result);

    }

    private void useNestedSelects() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

        Long result = queryFactory.select(count())
                .from(
                        SQLExpressions.select(order.userId)
                                .from(orderItem)
                                .innerJoin(orderItem.book, book)
                                .innerJoin(orderItem.order, order)
                                .where(book.price.gt(new BigDecimal("80")))
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
