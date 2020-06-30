package pl.exsio.querydsl.entityql.examples.jpa.entity;

import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName;
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User<T> {

    public enum Type {
        ADMIN, CLIENT
    }

    @Id
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Order order;

    @Column(name = "TYPE_STR", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeByName typeStr;

    @Column(name = "TYPE_ORD", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserTypeByOrdinal typeOrd;

    @Column(name = "CREATED_BY", columnDefinition = "varchar(15)")
    @org.hibernate.annotations.Type(type = "java.lang.String")
    private T createdBy;

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
