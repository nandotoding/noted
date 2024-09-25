package com.nandotoding.noted_account_service.controller;

import com.nandotoding.noted_account_service.model.request.AccountRequest;
import com.nandotoding.noted_account_service.model.request.LoginRequest;
import com.nandotoding.noted_account_service.model.request.LogoutRequest;
import com.nandotoding.noted_account_service.model.request.RegistrationRequest;
import com.nandotoding.noted_account_service.model.response.*;
import com.nandotoding.noted_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/registration")
    ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse = accountService.register(registrationRequest);
        SuccessResponse<RegistrationResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(registrationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/account/login")
    ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        LoginServiceResponse loginServiceResponse = accountService.login(loginRequest);
        SuccessResponse<LoginResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(new LoginResponse(loginServiceResponse.getId()));
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + loginServiceResponse.getToken())
                .body(successResponse);
    }

    @PostMapping("/account/logout")
    ResponseEntity logout(@RequestBody LogoutRequest logoutRequest) {
        LogoutResponse logoutResponse = accountService.logout(logoutRequest);
        SuccessResponse<LogoutResponse> successResponse = new SuccessResponse<>();
        successResponse.setCode(HttpStatus.OK.value());
        successResponse.setStatus(HttpStatus.OK.name());
        successResponse.setData(logoutResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/account/info")
    ResponseEntity info(@RequestBody AccountRequest accountRequest) {
        AccountInfoResponse accountInfoResponse = accountService.getAccountInfo(accountRequest);
        return ResponseEntity.status(HttpStatus.OK).body(accountInfoResponse);
    }
}
