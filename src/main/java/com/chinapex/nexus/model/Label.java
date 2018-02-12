package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by pengmingguo on 2/8/18
 */
@Entity
@Table(name = "t_label")
@Getter
@Setter
public class Label extends ManyToOrganization {
    private static final long serialVersionUID = -8433027354646728235L;
}
