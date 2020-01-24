package pl.exsio.querydsl.entityql.examples.jpa.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "COMPOSITE_PK")
public class CompositePk implements Serializable {

    @Id
    @Column(name = "ID_1")
    private Long id1;

    @Id
    @Column(name = "ID_2")
    private String id2;

    @Column(name = "DESC")
    private String desc;

    @OneToMany(mappedBy = "compositePk")
    private List<CompositeFk> compositeFks;

    public CompositePk() {
    }

    public CompositePk(Long id1, String id2, String desc) {
        this.id1 = id1;
        this.id2 = id2;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CompositePk{" +
                "id1=" + id1 +
                ", id2='" + id2 + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
