package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Accounts;

@Repository
public interface AccountsRespository extends JpaRepository<Accounts, Long> {

    public Accounts findByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);
}
