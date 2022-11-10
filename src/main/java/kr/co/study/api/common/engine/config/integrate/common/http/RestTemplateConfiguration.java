package kr.co.study.api.common.engine.config.integrate.common.http;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**   
 * @since       2020.03.10
 * @author      lucas
 * @description rest template configuration
 **********************************************************************************************************************/
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setReadTimeout   ( 5000);
        httpRequestFactory.setHttpClient    (HttpClientBuilder.create()
                .setMaxConnTotal   (200)
                .setMaxConnPerRoute(100)
                .build());
        return httpRequestFactory;
    }
}