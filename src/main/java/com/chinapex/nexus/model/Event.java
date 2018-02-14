package com.chinapex.nexus.model;

import java.util.Collections;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/**
 * created by pengmingguo on 2/10/18
 */
@Entity
@Table(name = "t_event",indexes = @Index(name = "uk_org_id_name",columnList = "org_id,name",unique = true))
@Getter
@Setter
public class Event extends ManyToOrganization{

    public static String REALTIME = "实时事件";
    public static String NON_REALTIME = "非实时事件";

    private String name;
    private String type;
    // 用户之前在网页上是否选择过
    private Boolean selected;

    @ManyToMany(mappedBy = "events")
    private Collection<EventGroup> groups = Collections.emptySet();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name = "config_id")
    private Config config;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
