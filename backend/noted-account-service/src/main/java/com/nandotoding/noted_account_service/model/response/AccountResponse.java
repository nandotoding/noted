package com.nandotoding.noted_account_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String id;
    private String name;
    private String username;
    private boolean isActive;
}
