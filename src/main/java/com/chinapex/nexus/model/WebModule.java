package com.chinapex.nexus.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * created by pengmingguo on 2/9/18
 */
@Entity
@Table(name = "web_module", indexes = @Index(name = "name_index", columnList = "name", unique = true))
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class WebModule extends BaseModel {

    private String name;

}
