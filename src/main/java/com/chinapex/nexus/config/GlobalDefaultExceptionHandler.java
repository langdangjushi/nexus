package com.chinapex.nexus.config;

import com.chinapex.nexus.util.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * created by pengmingguo on 2/11/18
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Msg defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        // 尊重用户自己用 @ReponseStatus定义的exception
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        logger.error("process url={} parameter={}", request.getRequestURI(), request.getParameterMap().toString(), e);
        return Msg.err().data(e.getMessage());
    }
}
