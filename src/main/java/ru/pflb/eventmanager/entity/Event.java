package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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
    @ManyToMany(targetEntity=User.class)
    private List<User> users;

}
