package com.fintech.UniTech.repository;

import com.fintech.UniTech.domain.Account;
import com.fintech.UniTech.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAccountByUserIdAndStatus(Long userId, AccountStatus status);

    Optional<Account> findByIdAndUserId(Long id, Long userId);
}
