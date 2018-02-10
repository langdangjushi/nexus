package com.chinapex.nexus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by pengmingguo on 2/10/18
 */
@Data
@AllArgsConstructor
public class CreateOrganizationResponse {
    private Boolean success;
    private String messages;
}
