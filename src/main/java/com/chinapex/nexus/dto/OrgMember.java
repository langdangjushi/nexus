package com.chinapex.nexus.dto;

import java.util.Date;
import lombok.Data;

/**
 * created by pengmingguo on 2/9/18
 */
@Data
public class OrgMember {
    private String userName;
    private String email;
    private Integer userId;
    private String role;
    private Integer status;
    private Date createdTime;
    private Date updatedTime;
}
