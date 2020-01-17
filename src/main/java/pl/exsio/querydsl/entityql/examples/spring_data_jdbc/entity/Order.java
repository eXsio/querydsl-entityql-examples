package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    @Id
    private final Long id;

    private final User user;

    private List<OrderItem> items = new ArrayList<>();

    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }


}
