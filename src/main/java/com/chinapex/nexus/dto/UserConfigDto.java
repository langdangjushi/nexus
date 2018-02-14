package com.chinapex.nexus.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 2/14/18
 */
@Getter
@Setter
public class UserConfigDto {
  private String email;

  private Collection<UserPrivilegeDto> privilege;

}
