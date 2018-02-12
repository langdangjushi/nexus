package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * created by pengmingguo on 2/8/18
 */
@Entity
@Table(name = "t_channel",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
)
@Getter
@Setter
public class Channel extends ManyToOrganization {
    private static final long serialVersionUID = -3806066551764728784L;


    private String name;

}
