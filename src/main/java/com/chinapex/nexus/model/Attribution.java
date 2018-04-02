package com.chinapex.nexus.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 3/29/18
 */
@Getter
@Setter
@Entity
@Table(name = "t_attribution")
public class Attribution extends ManyToOrganization {

  private String name;

  @ManyToOne
  @JoinColumn(name = "target", nullable = false)
  private Event target;

  private Integer cycle;

  @ManyToMany
  @JoinTable(name = "t_attribution_event_group"
      , joinColumns = @JoinColumn(name = "attri_id"),
      inverseJoinColumns = @JoinColumn(name = "event_group_id"))
  private Collection<EventGroup> eventGroups;

}
