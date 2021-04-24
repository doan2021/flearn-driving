package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.AppUser;

@Repository
public interface AppUserRespository extends JpaRepository<AppUser, Long> {

    public AppUser findByUserName(String userName);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);
}
