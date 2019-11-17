package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@Getter
@Setter
@ToString
@Entity
public class User extends AbstractEntity {

    @Column(name = "first_name", unique = true)
    private String firstName;

    @Column(name = "last_name", unique = true)
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(mappedBy = "users")
    private List<Event> events = new ArrayList<>();

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @Column(name = "wantsNewRole")
    private boolean wantsNewRole;

}
