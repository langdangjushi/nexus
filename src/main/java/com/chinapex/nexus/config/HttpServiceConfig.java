package com.chinapex.nexus.config;

import com.chinapex.nexus.service.PrismHttpService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

/**
 * created by pengmingguo on 2/12/18
 */
@Configuration
public class HttpServiceConfig {

    @URL
    @Value("${prism.address}")
    private String prismAdress;

    @Bean
    public PrismHttpService prismHttpService() {
        return new Retrofit.Builder()
                .baseUrl(prismAdress)
                .build()
                .create(PrismHttpService.class);
    }

}
