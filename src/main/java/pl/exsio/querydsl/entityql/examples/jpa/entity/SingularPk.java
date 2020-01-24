package pl.exsio.querydsl.entityql.examples.jpa.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SINGULAR_PK")
public class SingularPk {

    @Id
    @Column(name = "ID_1")
    private Long id1;

    @Column(name = "ID_2")
    private String id2;

    @Column(name = "DESC")
    private String desc;

    @OneToMany(mappedBy = "singularPk")
    private List<CompositeFk> compositeFks;

    public SingularPk() {
    }

    public SingularPk(Long id1, String id2, String desc) {
        this.id1 = id1;
        this.id2 = id2;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SingularPk{" +
                "id1=" + id1 +
                ", id2='" + id2 + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
