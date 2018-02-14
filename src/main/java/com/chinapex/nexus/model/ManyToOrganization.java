package com.chinapex.nexus.model;

import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** created by pengmingguo on 2/9/18 */
@Getter
@Setter
@MappedSuperclass
abstract class ManyToOrganization extends BaseModel {
  private static final long serialVersionUID = -4117490609088628467L;

  @ManyToOne
  @JoinColumn(name = "org_id", nullable = false)
  protected Organization organization;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ManyToOrganization)) {
      return false;
    }
    ManyToOrganization that = (ManyToOrganization) o;
    return Objects.equals(organization, that.organization);
  }

  @Override
  public int hashCode() {

    return Objects.hash(organization);
  }
}
