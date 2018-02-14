package com.chinapex.nexus.dto;

import lombok.Data;

/**
 * created by pengmingguo on 2/14/18
 */
@Data
public class UserPrivilegeDto {

  private Integer id;

  private Integer userId;

  private Integer resourceId;

  private Integer action  ;

  private String name;

}
