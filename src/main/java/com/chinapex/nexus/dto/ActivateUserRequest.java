package com.chinapex.nexus.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * created by pengmingguo on 2/10/18
 */
@Data
public class ActivateUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @Email
    private String email;

}
