package com.chinapex.nexus.dto;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

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

    private Set<UserPrivilegeDto> privilege;
}
