package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long> {

    public Account findByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);
}
