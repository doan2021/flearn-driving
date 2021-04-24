package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Role;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {
}
