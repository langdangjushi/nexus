package com.chinapex.nexus.dto;

import com.chinapex.nexus.model.User;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * created by pengmingguo on 2/9/18
 */
@Data
public class InviteUserRequest {
    @Email
    private String email;

    @Pattern(regexp="^admin|member$")
    private String role;

    @URL
    private String sendUrl;

    private Set<Integer> privilege;
}
