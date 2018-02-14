package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * created by pengmingguo on 2/13/18
 */
@Getter
@Setter
public class DataResourceTypeResponse {
    private String groupName;
    private Collection<DataResourceTypeDto> markets;
}
