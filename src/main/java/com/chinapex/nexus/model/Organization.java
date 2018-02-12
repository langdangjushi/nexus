package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * created by pengmingguo on 2/8/18
 */
@Entity
@Table(name = "t_organization",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
@Getter
@Setter
@ToString
public class Organization extends BaseModel {

    private static final long serialVersionUID = -5277519448130454763L;

    public static String TABLE_NAME = "organization";

    @Length(min = 1, max = 100)
    private String name;

    @Column(name = "prism_token")
    private String prismToken;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Collection<Label> labels;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Collection<DataResource> dataResources;

    @OneToMany(mappedBy = "organization")
    private Collection<Channel> channels;

    @OneToMany(mappedBy = "organization")
    private Collection<DataResourceType> dataResourceTypes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Collection<User> users;

    @OneToMany(mappedBy = "organization")
    private Collection<EventGroup> eventGroups;

    @OneToMany(mappedBy = "organization")
    private Collection<Event> events;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
