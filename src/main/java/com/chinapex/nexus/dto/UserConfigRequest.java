package com.chinapex.nexus.dto;

import java.util.Collection;
import javax.validation.constraints.Email;
import lombok.Data;

/**
 * created by pengmingguo on 2/14/18
 */
@Data
public class UserConfigRequest {
  @Email
  private String email;

  private Integer userId;

  private Collection<UserPrivilegeDto> privilege;
}
