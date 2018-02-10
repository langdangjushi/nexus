package com.chinapex.nexus.dto;

import com.chinapex.nexus.model.WebModule;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * created by pengmingguo on 2/10/18
 */
@Data
public class ResetUserRequest {
    @Email
    private String email;

    @NotNull
    private Integer userId;

    private Collection<WebModule> privilege;
}
