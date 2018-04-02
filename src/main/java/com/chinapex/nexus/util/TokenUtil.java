package com.chinapex.nexus.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinapex.nexus.dto.LoginToken;
import com.chinapex.nexus.exception.ForbiddenException;
import com.chinapex.nexus.model.User;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** created by pengmingguo on 2/9/18 */
public class TokenUtil {

  private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

  private TokenUtil() {}

  public static String TOKEN_NAME_TO = "NEXUS-TOKEN-TO";
  public static String TOKEN_NAME_FROM = "NEXUS-TOKEN-FROM";

  private static long LOGIN_TIMEOUT = 5 * 24 * 60 * 60 * 1000L; // 登录保持 5天在线

  private static String nonce = UUID.randomUUID().toString();

  public static HttpServletRequest getCurrentHttpRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes instanceof ServletRequestAttributes) {
      HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
      return request;
    }
    return null;
  }

  public static LoginToken getToken() {
    HttpServletRequest request = getCurrentHttpRequest();
    String header = request.getHeader(TOKEN_NAME_FROM);
    if (StringUtils.isBlank(header)) throw new ForbiddenException();
    header = header.substring(header.indexOf('.') + 1, header.lastIndexOf('.'));
    String jsonString = new String(Base64.getDecoder().decode(header));
    try {
      JSONObject obj = JSON.parseObject(jsonString);
      LoginToken token =
          JSON.parseObject(obj.getJSONObject("data").toJSONString(), LoginToken.class);

      //  token 过期失效
      if ((System.currentTimeMillis() - token.getCreateTime()) > LOGIN_TIMEOUT)
        throw new ForbiddenException();

      String checkStr = token.getOrgName() + token.getUserName() + token.getCreateTime() + nonce;
      // token 验签失败
      if (!DigestUtils.md5Hex(checkStr).equals(token.getSign())) throw new ForbiddenException();
      return token;
    } catch (Exception e) {
      logger.error("login token error {}", jsonString);
      throw new ForbiddenException();
    }
  }

  public static String createTokenStr(User user) {
    LoginToken token = new LoginToken();
    HashMap<String, Object> authInfo = new HashMap<>();
    authInfo.put("username", user.getName());
    token.setAuthInfo(authInfo);
    token.setUserName(user.getName());
    token.setOrgName(user.getOrganization().getName());
    token.setCreateTime(System.currentTimeMillis());
    String checkStr = token.getOrgName() + token.getUserName() + token.getCreateTime() + nonce;
    token.setSign(DigestUtils.md5Hex(checkStr));
    HashMap outer = new HashMap();
    outer.put("data", token);
    return "." + Base64.getEncoder().encodeToString(JSON.toJSONString(outer).getBytes()) + ".";
  }
}
