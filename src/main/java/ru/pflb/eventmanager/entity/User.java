package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @ManyToMany(targetEntity=Event.class,mappedBy="users",fetch=FetchType.LAZY)
    private List<Event> events;

    @Access(AccessType.FIELD)
    @ManyToMany(targetEntity=Role.class,mappedBy="users",fetch= FetchType.LAZY)
    private List<Role> roles;

}
