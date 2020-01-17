package pl.exsio.querydsl.entityql.examples.entity;

import javax.persistence.*;

@Entity
@Table(name = "GROUP_ADMINS")
public class GroupAdmin {

    @Id
    @Column(name = "GA_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;


    public GroupAdmin() {
    }

    public GroupAdmin(String name) {
        this.name = name;
    }
}
