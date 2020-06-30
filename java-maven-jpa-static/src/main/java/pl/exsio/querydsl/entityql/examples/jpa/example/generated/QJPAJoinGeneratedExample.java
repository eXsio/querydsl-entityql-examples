package pl.exsio.querydsl.entityql.examples.jpa.example.generated;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.jpa.entity.Book;
import pl.exsio.querydsl.entityql.examples.jpa.entity.Group;
import pl.exsio.querydsl.entityql.examples.jpa.entity.generated.*;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;

@Service
public class QJPAJoinGeneratedExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QJPAJoinGeneratedExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityONJoin() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

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
                .fetch();

        System.out.println(books);
    }

    private void getAllRowsFromAnEntityBasedOnFKJoin() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

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
                .fetch();

        System.out.println(books);
    }

    private void getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName() {

        QGroup group = QGroup.INSTANCE;
        QGroupAdmin groupAdmin = QGroupAdmin.INSTANCE;

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.id,
                                group.name
                        ))
                .from(group)
                .innerJoin(group.admin, groupAdmin)
                .where(groupAdmin.id.eq(2L))
                .fetch();

        System.out.println(groups);
    }

    private void getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause() {

        QGroup group = QGroup.INSTANCE;
        QUser user = QUser.INSTANCE;
        QUserGroup userGroup = QUserGroup.INSTANCE;

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.id,
                                group.name
                        ))
                .from(userGroup)
                .innerJoin(group).on(userGroup.groupId.eq(group.id))
                .innerJoin(user).on(userGroup.userId.eq(user.id))
                .where(user.id.eq(2L))
                .fetch();

        System.out.println(groups);

    }

    private void getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin() {

        QGroup group = QGroup.INSTANCE;
        QUser user = QUser.INSTANCE;
        QUserGroup userGroup = QUserGroup.INSTANCE;

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.id,
                                group.name
                        ))
                .from(userGroup)
                .innerJoin(userGroup.group, group)
                .innerJoin(userGroup.user, user)
                .where(user.id.eq(2L))
                .fetch();

        System.out.println(groups);
    }


    private void getAllRowsFromAnEntityBasedOnInverseFKJoin() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(order)
                .innerJoin(order.items, orderItem)
                .innerJoin(orderItem.book, book)
                .where(order.id.eq(2L))
                .fetch();

        System.out.println(books);
    }

    private void getAllRowsFromAnEntityBasedOnInverseFKJoinWithReferencedColumnName() {

        QBook book = QBook.INSTANCE;
        QOrder order = QOrder.INSTANCE;
        QOrderItem orderItem = QOrderItem.INSTANCE;

        List<Book> books = queryFactory.query()
                .select(
                        constructor(
                                Book.class,
                                book.id,
                                book.name,
                                book.desc,
                                book.price
                        ))
                .from(order)
                .innerJoin(order.itemsReferenced, orderItem)
                .innerJoin(orderItem.book, book)
                .where(order.id.eq(2L))
                .fetch();

        System.out.println(books);
    }



    @Override
    public void run() {
        getAllRowsFromAnEntityONJoin();
        getAllRowsFromAnEntityBasedOnFKJoin();
        getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName();
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause();
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin();
        getAllRowsFromAnEntityBasedOnInverseFKJoin();
        getAllRowsFromAnEntityBasedOnInverseFKJoinWithReferencedColumnName();
    }
}
