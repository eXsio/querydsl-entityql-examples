package pl.exsio.querydsl.entityql.examples.jpa.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "GROUPS")
public class Group {

    @Id
    @Column(name = "GROUP_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "USERS_GROUPS",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "ADMIN_NAME", nullable = false, referencedColumnName = "NAME")
    private GroupAdmin admin;

    public Group() {
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
