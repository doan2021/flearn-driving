package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findByUserName(String userName);

    Account findByEmail(String email);

    boolean existsByUserName(String userName);

	public Account findByAccountId(Long accountId);
	
	@Query("SELECT count(a) FROM Account a WHERE a.role.roleId = 2")
	Integer countAccount();
}
