package pl.exsio.querydsl.entityql.examples.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "COMPOSITE_PK")
class CompositePk implements Serializable {

    @Id
    @Column(name = "ID_1")
    private Long id1

    @Id
    @Column(name = "ID_2")
    private String id2

    @Column(name = "DESC")
    private String desc

    @OneToMany(mappedBy = "compositePk")
    private List<CompositeFk> compositeFks

    CompositePk() {
    }

    CompositePk(Long id1, String id2, String desc) {
        this.id1 = id1
        this.id2 = id2
        this.desc = desc
    }

    Long getId1() {
        id1
    }

    String getId2() {
        id2
    }

    String getDesc() {
        desc
    }
}