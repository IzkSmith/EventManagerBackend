package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private City city;

}
