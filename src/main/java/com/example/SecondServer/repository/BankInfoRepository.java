package com.example.SecondServer.repository;

import com.example.SecondServer.model.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Integer> {
    boolean existsByBic(String bic);
    BankInfo findFirstByBic(String bic);
}
