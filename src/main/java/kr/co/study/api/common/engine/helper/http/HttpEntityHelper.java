package kr.co.study.api.common.engine.helper.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @since       2020.03.06
 * @author      lucas
 * @description http entity helper
 **********************************************************************************************************************/
@Component
public class HttpEntityHelper {

    public static HttpEntity getHttpEntity(Map<String, String> headers) {
        HttpHeaders header = new HttpHeaders();
        for(Map.Entry<String, String> entry : headers.entrySet()){
            header.add(entry.getKey(), entry.getValue());
        }

        return new HttpEntity(header);
    }

    public static HttpEntity getHttpEntity(Map<String, String> headers, Map<String, String> params) {
        HttpHeaders header = new HttpHeaders();
        for(Map.Entry<String, String> entry : headers.entrySet()){
            header.add(entry.getKey(), entry.getValue());
        }

        return new HttpEntity(params, header);
    }
}