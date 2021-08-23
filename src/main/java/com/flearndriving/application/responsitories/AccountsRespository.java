package com.flearndriving.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.application.dto.AccountLogin;
import com.flearndriving.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    @Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.userName = :userName AND a.role.roleId = 2")
    Account findByUserName(String userName);
    
    @Query(value="SELECT new com.flearndriving.application.dto.AccountLogin(a.accountId, "
            + "a.userName, "
            + "a.firstName, "
            + "a.middleName, "
            + "a.lastName, "
            + "a.email, "
            + "a.description) "
            + "FROM Account a "
            + "WHERE a.role.roleId = 2 "
            + "AND a.isDelete = false "
            + "AND a.userName = :userName")
    AccountLogin findBasicInfoByUserName(String userName);
    
    @Query(value="SELECT new com.flearndriving.application.dto.AccountLogin(a.accountId, "
            + "a.userName, "
            + "a.firstName, "
            + "a.middleName, "
            + "a.lastName, "
            + "a.email, "
            + "a.description) "
            + "FROM Account a "
            + "WHERE a.role.roleId = 2 "
            + "AND a.isDelete = false "
            + "AND a.email = :email")
    AccountLogin findBasicInfoByEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.email = :email AND a.role.roleId = 2")
    Account findByEmail(String email);

    @Query("SELECT count(a) > 0 FROM Account a WHERE a.isDelete = false AND a.userName = :userName AND a.role.roleId = 2")
    boolean existsByUserName(String userName);
	
	@Query("SELECT count(a) FROM Account a WHERE a.role.roleId = 2")
	Integer countAccount();
}
