package it.interno.scomparsibatch.utils;

import it.interno.scomparsibatch.dto.ResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCalls {

    private RestCalls() {
    }

    public static void doPost(String url, Object body){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body), ResponseDto.class);
    }
}
