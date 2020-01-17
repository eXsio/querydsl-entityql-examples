package pl.exsio.querydsl.entityql.examples.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP_ADMINS")
public class GroupAdmin {

    @Id
    @Column(name = "GA_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;


    public GroupAdmin() {
    }

    public GroupAdmin(String name) {
        this.name = name;
    }
}
