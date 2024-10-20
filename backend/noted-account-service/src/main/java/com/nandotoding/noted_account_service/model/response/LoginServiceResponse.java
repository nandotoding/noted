package com.nandotoding.noted_account_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginServiceResponse {
    private String id;
    private String token;
}
