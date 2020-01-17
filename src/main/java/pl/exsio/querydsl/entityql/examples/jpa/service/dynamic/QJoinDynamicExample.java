package pl.exsio.querydsl.entityql.examples.jpa.service.dynamic;

import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Service;
import pl.exsio.querydsl.entityql.EntityQL;
import pl.exsio.querydsl.entityql.Q;
import pl.exsio.querydsl.entityql.examples.Example;
import pl.exsio.querydsl.entityql.examples.jpa.entity.*;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static pl.exsio.querydsl.entityql.EntityQL.dto;

@Service
public class QJoinDynamicExample implements Example {

    private final SQLQueryFactory queryFactory;

    public QJoinDynamicExample(SQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private void getAllRowsFromAnEntityONJoin() {

        Q<Book> book = EntityQL.qEntity(Book.class);
        Q<Order> order = EntityQL.qEntity(Order.class);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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
        Q<Book> book = EntityQL.qEntity(Book.class);
        Q<Order> order = EntityQL.qEntity(Order.class);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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

        Q<Book> book = EntityQL.qEntity(Book.class);
        Q<Order> order = EntityQL.qEntity(Order.class);
        Q<OrderItem> orderItem = EntityQL.qEntity(OrderItem.class);

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

    private void getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName() {

        Q<Group> group = EntityQL.qEntity(Group.class);
        Q<GroupAdmin> groupAdmin = EntityQL.qEntity(GroupAdmin.class);

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(group)
                .innerJoin(group.<GroupAdmin>joinColumn("admin"), groupAdmin)
                .where(groupAdmin.longNumber("id").eq(2L))
                .fetch();

        System.out.println(groups);
    }

    private void getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause() {

        Q<Group> group = EntityQL.qEntity(Group.class);
        Q<User> user = EntityQL.qEntity(User.class);
        Q<UserGroup> userGroup = EntityQL.qEntity(UserGroup.class);

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(userGroup)
                .innerJoin(group).on(userGroup.longNumber("groupId").eq(group.longNumber("id")))
                .innerJoin(user).on(userGroup.longNumber("userId").eq(user.longNumber("id")))
                .where(user.longNumber("id").eq(2L))
                .fetch();

        System.out.println(groups);

    }

    private void getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin() {

        Q<Group> group = EntityQL.qEntity(Group.class);
        Q<User> user = EntityQL.qEntity(User.class);
        Q<UserGroup> userGroup = EntityQL.qEntity(UserGroup.class);

        List<Group> groups = queryFactory.query()
                .select(
                        constructor(
                                Group.class,
                                group.longNumber("id"),
                                group.string("name")
                        ))
                .from(userGroup)
                .innerJoin(userGroup.<Group>joinColumn("group"), group)
                .innerJoin(userGroup.<User>joinColumn("user"), user)
                .where(user.longNumber("id").eq(2L))
                .fetch();

        System.out.println(groups);
    }


    @Override
    public void run() {
        getAllRowsFromAnEntityONJoin();
        getAllRowsUsingListOfColumnNames();
        getAllRowsFromAnEntityBasedOnFKJoin();
        getAllRowsFromAnEntityBasedOnFKJoinWithCustomReferencedColumnName();
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingONClause();
        getAllRowsFromAnEntityBasedOnJoinTableMappingUsingFKJoin();
    }
}
