package com.nandotoding.noted_account_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private boolean isActive;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime lastLogin;
    private boolean isDeleted;
}
