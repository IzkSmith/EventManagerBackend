package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Event extends AbstractEntity {

    private String name;
    private String date;
    private int maxMembers;
    private String description;

    @Access(AccessType.FIELD)
    @ManyToOne(targetEntity=City.class)
    private City city;

    @Access(AccessType.FIELD)
    @ManyToMany(cascade = {CascadeType.DETACH},targetEntity=User.class,fetch= FetchType.LAZY)
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
