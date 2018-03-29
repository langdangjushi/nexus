package com.chinapex.nexus.model;

import java.util.Collection;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
  private Collection<Event> events;


  @Override
  public String toString() {
    return "EventGroup{" +
        "name='" + name + '\'' +
        ", id=" + id +
        '}';
  }

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
