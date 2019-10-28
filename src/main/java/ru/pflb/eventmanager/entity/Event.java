package ru.pflb.eventmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EVENT")
@Getter
@Setter
@ToString
@Entity
public class Event extends AbstractEntity {

    @Column(name = "name", length = 1000)
    private String name;

    @Column(name = "date", length = 1000)
    private String date;

    @Column(name = "max_members", length = 1000)
    private int maxMembers;

    @Column(name = "description",length = 2048)
    private String description;

    @Column(name = "holder_id", length = 1000)
    private Long holderId;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users =new ArrayList<>();

}
