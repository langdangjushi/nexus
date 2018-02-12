package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by pengmingguo on 2/9/18
 */
@Entity
@Table(name = "t_data_resource")
@Getter
@Setter
public class DataResource extends ManyToOrganization {

    private static final long serialVersionUID = -5235933824427666763L;
}
