package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity

import org.springframework.data.annotation.Id

import javax.annotation.Nonnull

class Order implements Serializable {

    @Id
    private final Long id

    @Nonnull
    private final User user
    private List<OrderItem> items = new ArrayList<>()

    Long getId() {
        id
    }

    List<OrderItem> getItems() {
        items
    }

    User getUser() {
        user
    }
}
