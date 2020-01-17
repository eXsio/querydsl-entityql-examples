package pl.exsio.querydsl.entityql.examples.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
