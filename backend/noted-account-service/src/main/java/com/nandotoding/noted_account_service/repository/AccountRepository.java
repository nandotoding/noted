package com.nandotoding.noted_account_service.repository;

import com.nandotoding.noted_account_service.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "SELECT a.id FROM accounts a WHERE a.username = :username AND a.is_deleted = false", nativeQuery = true)
    String getId(String username);

    @Query(value = "SELECT a.password FROM accounts a WHERE a.id = :id", nativeQuery = true)
    String getPassword(String id);

    @Query(value = "SELECT a.is_deleted FROM accounts a WHERE a.id = :id", nativeQuery = true)
    boolean isDeleted(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE accounts SET is_active = :isActive WHERE id = :id", nativeQuery = true)
    void setActive(String id, boolean isActive);

    @Transactional
    @Modifying
    @Query(value = "UPDATE accounts SET last_login = :timestamp WHERE id = :id", nativeQuery = true)
    void setLastLogin(String id, OffsetDateTime timestamp);

    @Query(value = "SELECT * FROM accounts a WHERE a.id = :id", nativeQuery = true)
    Account getAccount(String id);
}
