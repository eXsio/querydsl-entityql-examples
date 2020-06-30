package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity;

import org.springframework.data.annotation.Id;

public class Group {

    @Id
    private Long id;
    private String name;
    private GroupAdmin admin;

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }
}
