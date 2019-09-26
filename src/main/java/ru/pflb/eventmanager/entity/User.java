package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User extends AbstractEntity {

    @Column(name = "username", unique = true)
    private String username;
    private String password;
    @Column(name = "email", unique = true)
    private String email;

    @Access(AccessType.FIELD)
    @ManyToMany(cascade = {CascadeType.DETACH},targetEntity=Event.class,fetch= FetchType.LAZY)
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @Access(AccessType.FIELD)
    @ManyToMany(cascade = {CascadeType.DETACH},targetEntity=Role.class,fetch= FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}
