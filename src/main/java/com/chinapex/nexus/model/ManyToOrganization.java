package com.chinapex.nexus.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * created by pengmingguo on 2/9/18
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
abstract class ManyToOrganization extends BaseModel{
    private static final long serialVersionUID = -4117490609088628467L;
    @ManyToOne
    @JoinColumn(name = "org_id",nullable = false)
    protected Organization organization;
}
