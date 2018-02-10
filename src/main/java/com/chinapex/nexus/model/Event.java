package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by pengmingguo on 2/10/18
 */
@Entity
@Table(name = "event")
@Getter
@Setter
public class Event extends ManyToOrganization{

    public static String REALTIME = "实时事件";
    public static String NON_REALTIME = "非实时事件";

    private String name;
    private String type;


}
