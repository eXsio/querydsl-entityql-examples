package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName;
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal;

import java.util.Date;


public class User<T> {

    @Column("USER_ID")
    @Id
    private final Long id;
    private final String name;
    private final Order order;
    private final UserTypeByName typeStr;
    private final UserTypeByOrdinal typeOrd;
    private final T createdBy;
    private final Date createdAt;

    User(Long id, String name, Order order, UserTypeByName typeStr, UserTypeByOrdinal typeOrd, T createdBy, Date createdAt) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.typeStr = typeStr;
        this.typeOrd = typeOrd;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
