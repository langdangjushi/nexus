package com.chinapex.nexus.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.junit.Test;

/**
 * created by pengmingguo on 4/2/18
 */
public class JwtTest {

  @Test
  public void testjwt() {
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret");
      String token = JWT.create()
          .withClaim("a","b")
          .withExpiresAt(new Date())
          .sign(algorithm);
      System.out.println(token);
    } catch (UnsupportedEncodingException exception){
      //UTF-8 encoding not supported
    } catch (JWTCreationException exception){
      //Invalid Signing configuration / Couldn't convert Claims.
    }
  }

}
