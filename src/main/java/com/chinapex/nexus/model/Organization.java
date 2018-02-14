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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
    private Collection<Label> labels;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
    private Collection<DataResource> dataResources;

    @OneToMany(mappedBy = "organization")
    private Collection<Channel> channels;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "t_org_data_resource_type", joinColumns = @JoinColumn(name = "org_id"), inverseJoinColumns = @JoinColumn(name = "data_resource_type_id"))
    private Collection<DataResourceType> dataResourceTypes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
    private Collection<User> users;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<EventGroup> eventGroups;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
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
