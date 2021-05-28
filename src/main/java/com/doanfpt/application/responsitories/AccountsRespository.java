package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long> {

    public Account findByUserName(String userName);

    Account findByEmail(String email);

    Boolean existsByUserName(String userName);
}