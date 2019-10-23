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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CITY")
@Getter
@Setter
@ToString
@Entity
public class City extends AbstractEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Access(AccessType.FIELD)
    @OneToMany(mappedBy="city")
    private List<Event> events;

}
