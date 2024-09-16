package com.nandotoding.noted_account_service.service.implementation;

import com.nandotoding.noted_account_service.exception.NotFoundException;
import com.nandotoding.noted_account_service.exception.UnauthorizedException;
import com.nandotoding.noted_account_service.model.entity.Account;
import com.nandotoding.noted_account_service.model.request.AccountRequest;
import com.nandotoding.noted_account_service.model.request.LoginRequest;
import com.nandotoding.noted_account_service.model.request.LogoutRequest;
import com.nandotoding.noted_account_service.model.request.RegistrationRequest;
import com.nandotoding.noted_account_service.model.response.AccountResponse;
import com.nandotoding.noted_account_service.model.response.LoginServiceResponse;
import com.nandotoding.noted_account_service.model.response.LogoutResponse;
import com.nandotoding.noted_account_service.model.response.RegistrationResponse;
import com.nandotoding.noted_account_service.repository.AccountRepository;
import com.nandotoding.noted_account_service.service.AccountService;
import com.nandotoding.noted_account_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class AccountServiceImplementation implements AccountService {
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository, JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        if (accountRepository.getId(registrationRequest.getUsername()) != null) {
            throw new UnauthorizedException("Username Already Registered");
        }

        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setName(registrationRequest.getName());
        account.setUsername(registrationRequest.getUsername());
        account.setPassword(registrationRequest.getPassword());
        account.setActive(false);
        account.setDeleted(false);
        accountRepository.save(account);
        return new RegistrationResponse(account.getId());
    }

    @Override
    public LoginServiceResponse login(LoginRequest loginRequest) {
        String id = accountRepository.getId(loginRequest.getUsername());

        if (id == null || !loginRequest.getPassword().equals(accountRepository.getPassword(id))) {
            throw new UnauthorizedException("Invalid Username or Password");
        }

        String token = jwtUtil.generateToken(id);
        accountRepository.setActive(id, true);
        accountRepository.setLastLogin(id, OffsetDateTime.now(ZoneOffset.ofHours(7)));
        return new LoginServiceResponse(id, token);
    }

    @Override
    public LogoutResponse logout(LogoutRequest logoutRequest) {
        Account account = accountRepository.getAccount(logoutRequest.getId());

        if (account == null) {
            throw new UnauthorizedException();
        }

        accountRepository.setActive(account.getId(), false);
        return new LogoutResponse(account.getId());
    }

    @Override
    public AccountResponse getAccount(AccountRequest accountRequest) {
        Account account = accountRepository.getAccount(accountRequest.getId());

        if (account == null) {
            throw new NotFoundException();
        }

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setName(account.getName());
        accountResponse.setUsername(account.getUsername());
        accountResponse.setActive(account.isActive());
        return accountResponse;
    }
}
