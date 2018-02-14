package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

/**
 * created by pengmingguo on 2/9/18
 */
@Entity
@Table(name = "t_data_resource_type",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Getter
@Setter
public class DataResourceType extends BaseModel {
    private static final long serialVersionUID = 2983431492292910689L;

    @NotNull
    private String name;

    @Column(name = "group_name")
    private String groupName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DataResourceType that = (DataResourceType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name);
    }
}
