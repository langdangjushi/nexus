package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * created by pengmingguo on 2/13/18
 */
@Getter
@Setter
public class PrismContainer {
    private Integer containerId;
    private String containerName;
    private Collection<PrismTrigger> triggers;
}
