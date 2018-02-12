package com.chinapex.nexus.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by pengmingguo on 2/11/18
 */
@Configuration
public class FastJsonConverter {

    /**
     * 使用 fastJson 来做http request response body 的 converter
     * @return
     */
    @Bean
    public HttpMessageConverters fastJson() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        return new HttpMessageConverters(converter);
    }
}
