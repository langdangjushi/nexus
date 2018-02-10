package com.chinapex.nexus.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by pengmingguo on 2/10/18
 */
@Data
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
