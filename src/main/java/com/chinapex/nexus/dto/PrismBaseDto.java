package com.chinapex.nexus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 2/13/18
 */
@Getter
@Setter
public class PrismBaseDto {
    private Integer pk;
    private String name;
    private String uuid;

    // for prism trigger
    private Integer rule_number;
}
