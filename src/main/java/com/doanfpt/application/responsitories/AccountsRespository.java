package com.doanfpt.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
=======
import org.springframework.data.jpa.repository.Query;
>>>>>>> 69cffd0d7f1c82870c781d82d176df4c81864003
import org.springframework.stereotype.Repository;

import com.doanfpt.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findByUserName(String userName);

    Account findByEmail(String email);

    Boolean existsByUserName(String userName);

	public Account findByAccountIdAndIsDelete(Long accountId, boolean isNotDelete);
	
	@Query("SELECT count(a) FROM Account a WHERE a.isDelete = false and a.role.roleId = 2")
	Integer countAccount();
}
