package com.chinapex.nexus.dto;

import java.util.Map;
import lombok.Data;

/**
 * created by pengmingguo on 2/9/18
 */
@Data
public class LoginToken {

    private String orgName;
    private String userName;
    private Long createTime;
    private String sign;
    private Map<String,Object> authInfo;

}
