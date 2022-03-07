package com.fintech.UniTech.repository;

import com.fintech.UniTech.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
