package com.chinapex.nexus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by pengmingguo on 2/9/18
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "please login in!")
public class ForbiddenException extends RuntimeException {
}
