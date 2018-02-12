package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * created by pengmingguo on 2/11/18
 */
@Entity
@Table(name = "t_event_group",indexes = {@Index(name = "name_index",columnList = "name",unique = true)})
@Getter
@Setter
public class EventGroup extends ManyToOrganization {
    private String name;

    @ManyToMany
    @JoinTable(name = "event_group_relation",joinColumns = @JoinColumn(name = "event_group_id"),inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Collection<Event> events;
}
