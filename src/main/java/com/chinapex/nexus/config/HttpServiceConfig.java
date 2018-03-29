package com.chinapex.nexus.config;

import com.chinapex.nexus.service.PrismHttpService;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/** created by pengmingguo on 2/12/18 */
@Configuration
public class HttpServiceConfig {

  @URL
  @Value("${prism.address}")
  private String prismAdress;

  @Bean
  public PrismHttpService prismHttpService() {
    OkHttpClient client =
        new OkHttpClient.Builder()
            .addInterceptor(
                new Interceptor() {
                  @Override
                  public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    String token = originalRequest.header("token");
                    Request newRequest =
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "token " + token)
                            .build();
                    return chain.proceed(newRequest);
                  }
                })
            .build();
    return new Retrofit.Builder()
        .client(client)
        .baseUrl(prismAdress)
        .addConverterFactory(FastJsonConverterFactory.create())
        .build()
        .create(PrismHttpService.class);
  }
}
