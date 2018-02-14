package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * created by pengmingguo on 2/13/18
 */
@Getter
@Setter
public class PrismProjectResponse {
    private Integer projectId;
    private String projectName;

    Collection<PrismContainer> containers;

    Collection<String> pixels;
}
