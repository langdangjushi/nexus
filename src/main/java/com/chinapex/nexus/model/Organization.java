package com.chinapex.nexus.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;

/** created by pengmingguo on 2/8/18 */
@Entity
@Table(name = "t_organization", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Getter
@Setter
public class Organization extends BaseModel {

  private static final long serialVersionUID = -5277519448130454763L;

  public static String TABLE_NAME = "organization";

  @Length(min = 1, max = 100)
  private String name;

  @Column(name = "prism_token")
  private String prismToken;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
  private Collection<Label> labels ;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
  private Collection<DataResource> dataResources = Collections.emptySet();

  @OneToMany(mappedBy = "organization")
  private Collection<Channel> channels ;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
    name = "t_org_data_resource_type",
    joinColumns = @JoinColumn(name = "org_id"),
    inverseJoinColumns = @JoinColumn(name = "data_resource_type_id")
  )
  private Collection<DataResourceType> dataResourceTypes ;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization", orphanRemoval = true)
  private Collection<User> users ;

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<EventGroup> eventGroups ;

  @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<Event> events ;

  public void addEvent(Event event) {
    if (this.events.size() == 0) this.events = new HashSet<>();
    event.setOrganization(this);
    this.events.add(event);
  }

  public void addEventGroup(EventGroup eventGroup) {
    if (CollectionUtils.isEmpty(this.eventGroups)) eventGroups = new HashSet<>();
    eventGroup.setOrganization(this);
    this.eventGroups.add(eventGroup);
  }

  public void addLabel(Label obj) {
    if(CollectionUtils.isEmpty(this.labels)) this.labels = new HashSet<>();
    this.labels.add(obj);
  }

  @Override
  public String toString() {
    return "Organization{" +
        "name='" + name + '\'' +
        ", prismToken='" + prismToken + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Organization)) {
      return false;
    }
    Organization that = (Organization) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

}
