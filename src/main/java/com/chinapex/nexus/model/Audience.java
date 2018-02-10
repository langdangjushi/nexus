package com.chinapex.nexus.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by pengmingguo on 2/9/18
 */
@Entity
@Table(name = "audience")
@Data
public class Audience extends ManyToOrganization{
}
