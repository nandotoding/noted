package com.nandotoding.noted_account_service.service;

import com.nandotoding.noted_account_service.model.request.AccountRequest;
import com.nandotoding.noted_account_service.model.request.LoginRequest;
import com.nandotoding.noted_account_service.model.request.LogoutRequest;
import com.nandotoding.noted_account_service.model.request.RegistrationRequest;
import com.nandotoding.noted_account_service.model.response.AccountResponse;
import com.nandotoding.noted_account_service.model.response.LoginServiceResponse;
import com.nandotoding.noted_account_service.model.response.LogoutResponse;
import com.nandotoding.noted_account_service.model.response.RegistrationResponse;

public interface AccountService {
    RegistrationResponse register(RegistrationRequest registrationRequest);
    LoginServiceResponse login(LoginRequest loginRequest);
    LogoutResponse logout(LogoutRequest logoutRequest);
    AccountResponse getAccount(AccountRequest accountRequest);
}
