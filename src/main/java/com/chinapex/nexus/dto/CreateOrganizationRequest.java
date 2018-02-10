package com.chinapex.nexus.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * created by pengmingguo on 2/10/18
 */
@Data
public class CreateOrganizationRequest {
    @Email
    private String email;

    @NotNull
    private String password;

    // orgName 格式为字母数字下划线，且不能数字开头
    @NotNull
    @Pattern(regexp = "^[a-zA-Z_]+\\w*$", message = "orgName format invalid!!!")
    private String orgName;

    // prism token, can be null
    private String token;
}
