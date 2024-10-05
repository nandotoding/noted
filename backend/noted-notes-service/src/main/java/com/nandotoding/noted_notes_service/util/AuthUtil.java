package com.nandotoding.noted_notes_service.util;

import com.nandotoding.noted_notes_service.model.request.AccountInfoRequest;
import com.nandotoding.noted_notes_service.model.response.AccountInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtil {
    @Value("${uri.service.acc}")
    private String accountServiceUri;
    private final RestTemplate restTemplate;

    @Autowired
    public AuthUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AccountInfoResponse getAccountInfo(AccountInfoRequest request) {
        String url = accountServiceUri + "/account/info";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<AccountInfoRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<AccountInfoResponse> response = restTemplate.postForEntity(url, entity, AccountInfoResponse.class);
        return response.getBody();
    }

    public boolean isActiveSession(AccountInfoRequest request) {
        return this.getAccountInfo(request).isActive();
    }
}
