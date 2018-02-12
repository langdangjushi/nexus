package com.chinapex.nexus.dto;

import lombok.Data;

/**
 * created by pengmingguo on 2/11/18
 */
@Data
public class EventDto {
    private Integer id;
    private String eventName;
    private String eventType;

    // 用户上次是否在网页里选中
    private Boolean selected;
}
