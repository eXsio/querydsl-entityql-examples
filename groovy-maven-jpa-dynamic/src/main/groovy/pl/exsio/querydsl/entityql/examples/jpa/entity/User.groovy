package pl.exsio.querydsl.entityql.examples.jpa.entity

import org.hibernate.annotations.Type
import pl.exsio.querydsl.entityql.examples.enums.by_name.UserTypeByName
import pl.exsio.querydsl.entityql.examples.enums.by_ordinal.UserTypeByOrdinal

import javax.persistence.*

@Entity
@Table(name = "USERS")
class User<T> {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private Long id

    @Column(name = "NAME")
    private String name

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Order order

    @Column(name = "TYPE_STR", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeByName typeStr

    @Column(name = "TYPE_ORD", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserTypeByOrdinal typeOrd

    @Column(name = "CREATED_BY", columnDefinition = "varchar(15)")
    @Type(type = "java.lang.String")
    private T createdBy

    @Column(name="CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt

    @Column(name="ENABLED")
    private Boolean enabled

    User() {
    }

    User(String name) {
        this.name = name
    }

    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    String getName() {
        name
    }

    void setName(String name) {
        this.name = name
    }

    Order getOrder() {
        order
    }

    void setOrder(Order order) {
        this.order = order
    }

    UserTypeByName getTypeStr() {
        typeStr
    }

    void setTypeStr(UserTypeByName typeStr) {
        this.typeStr = typeStr
    }

    UserTypeByOrdinal getTypeOrd() {
        typeOrd
    }

    void setTypeOrd(UserTypeByOrdinal typeOrd) {
        this.typeOrd = typeOrd
    }


    Date getCreatedAt() {
        createdAt
    }

    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt
    }

    void setCreatedBy(T createdBy) {
        this.createdBy = createdBy
    }
}
