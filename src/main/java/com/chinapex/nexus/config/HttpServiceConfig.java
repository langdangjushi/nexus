package com.chinapex.nexus.config;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * created by pengmingguo on 2/12/18
 */
@Configuration
public class HttpServiceConfig {

    @URL
    @Value("${prism.address}")
    private String prismAdress;

}
