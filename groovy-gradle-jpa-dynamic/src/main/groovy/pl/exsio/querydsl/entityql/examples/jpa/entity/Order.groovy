package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order implements Serializable {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue
    private Long id

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>()

    @OneToMany(mappedBy = "orderReferenced", cascade = CascadeType.ALL)
    private List<OrderItem> itemsReferenced = new ArrayList<>()


    Long getId() {
        id
    }

    void setId(Long id) {
        this.id = id
    }

    List<OrderItem> getItems() {
        items
    }

    void setItems(List<OrderItem> items) {
        this.items = items
    }

    void addItem(OrderItem item) {
        items.add(item)
        item.setShoppingOrder(this)
    }

    User getUser() {
        user
    }

    void setUser(User user) {
        this.user = user
    }

}
