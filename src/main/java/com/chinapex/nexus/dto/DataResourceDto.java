package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * created by pengmingguo on 2/13/18
 */
@Getter
@Setter
public class DataResourceDto {
    private String name;

    private String dataType;

    private String syncStatus;

    private Date lastSyncTime;

    private Integer tableCount;
}
