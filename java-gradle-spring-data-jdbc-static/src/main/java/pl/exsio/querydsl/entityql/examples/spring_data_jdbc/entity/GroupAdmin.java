package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class GroupAdmin {

    @Id
    @Column("GA_ID")
    private final Long id;
    private final String name;

    public GroupAdmin(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupAdmin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
