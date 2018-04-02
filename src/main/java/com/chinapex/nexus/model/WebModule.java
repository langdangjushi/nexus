package com.chinapex.nexus.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "t_web_module", indexes = @Index(name = "name_index", columnList = "name", unique = true))
@Getter
@Setter
public class WebModule {

    @Id
    private Integer id;

    private String name;

}
