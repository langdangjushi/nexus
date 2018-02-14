package com.chinapex.nexus.model;

import java.util.Collections;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/** created by pengmingguo on 2/11/18 */
@Entity
@Table(
  name = "t_event_group",
  indexes = {@Index(name = "uk_org_id_name", columnList = "org_id,name", unique = true)}
)
@Getter
@Setter
public class EventGroup extends ManyToOrganization {

  private static final long serialVersionUID = -6922138315418203314L;
  private String name;

  @ManyToMany
  @JoinTable(
    name = "t_event_group_relation",
    joinColumns = @JoinColumn(name = "event_group_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id")
  )
  private Collection<Event> events = Collections.emptySet();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EventGroup)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    EventGroup that = (EventGroup) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), name);
  }
}
