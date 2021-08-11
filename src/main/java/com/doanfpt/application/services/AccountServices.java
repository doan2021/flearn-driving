package com.doanfpt.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doanfpt.application.common.Common;
import com.doanfpt.application.common.Constant;
import com.doanfpt.application.dto.AccountForm;
import com.doanfpt.application.dto.AccountUpdateForm;
import com.doanfpt.application.entities.Account;
import com.doanfpt.application.entities.Role;
import com.doanfpt.application.enums.AuthenticationProvider;
import com.doanfpt.application.model.AccountPrincipal;
import com.doanfpt.application.responsitories.AccountsRespository;
import com.doanfpt.application.responsitories.RoleRespository;
import com.doanfpt.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

	@Autowired
	private AccountsRespository accountsRespository;

	@Autowired
	private RoleRespository roleRespository;

	public Account getAccountByUserName(String userName) {
		return accountsRespository.findByUserName(userName);
	}

	public List<Account> findAllAccount() {
		List<Account> listUser = accountsRespository.findAll();
		if (listUser != null) {
			return listUser;
		}
		return new ArrayList<Account>();
	}

	public Account findByEmail(String email) {
		return accountsRespository.findByEmail(email);
	}

	@Transactional
	public void createAccount(AccountForm appUserForm) {
		String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
		Account account = new Account();
		account.setUserName(appUserForm.getUserName());
		account.setFirstName(appUserForm.getFirstName());
		account.setLastName(appUserForm.getLastName());
		account.setEmail(appUserForm.getEmail());
		account.setGender(appUserForm.getGender());
		account.setEncrytedPassword(encrytedPassword);
		Role role = roleRespository.getOne(new Long(2));
		account.setRole(role);
		accountsRespository.save(account);
	}

	public Account getAccountLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
		return accountsRespository.findByEmail(loginedUser.getEmail());
	}

	@Transactional
	public void createAccountAfterOAuthLoginSuccess(String email, String firstName, String lastName,
			String authenticationProvider) {
		Account account = new Account();
		account.setUserName(email);
		account.setEmail(email);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setDelete(false);
		account.setAuthProvider(AuthenticationProvider.GOOGLE.toString());
		account.setEncrytedPassword("");
		Role role = roleRespository.getOne(new Long(2));
		account.setRole(role);
		accountsRespository.save(account);
	}

	@Transactional
	public void updateAccountAfterOAuthLoginSuccess(Account account, String authenticationProvider) {
		account.setAuthProvider(AuthenticationProvider.GOOGLE.toString());
		accountsRespository.save(account);
	}

	public Object getObjectUpdate(Long accountId) {
		AccountForm accountForm = new AccountForm();
		Account account = accountsRespository.findByAccountIdAndIsDelete(accountId, Constant.IS_NOT_DELETE);
		accountForm.setAccountId(account.getAccountId());
		accountForm.setFirstName(account.getFirstName());
		accountForm.setMiddleName(account.getMiddleName());
		accountForm.setLastName(account.getLastName());
		accountForm.setUserName(account.getUserName());
		accountForm.setBirthDay(account.getBirthDay() == null ? StringUtils.EMPTY : DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
		accountForm.setNumberPhone(account.getNumberPhone());
		accountForm.setEmail(account.getEmail());
		accountForm.setGender(account.getGender());
		accountForm.setDescription(account.getDescription());
		accountForm.setRoleId(account.getRole().getRoleId());
		return accountForm;
	}

	@Transactional
	public boolean updateAccount(AccountUpdateForm accountUpdateForm) {
		if (accountUpdateForm == null) {
			return false;
		}
		Account account = accountsRespository.findByAccountIdAndIsDelete(accountUpdateForm.getAccountId(), Constant.IS_NOT_DELETE);
		if (account == null) {
			return false;
		}
		account.setFirstName(accountUpdateForm.getFirstName());
		account.setMiddleName(accountUpdateForm.getMiddleName());
		account.setLastName(accountUpdateForm.getLastName());
		account.setBirthDay(Common.stringToDate(accountUpdateForm.getBirthDay()));
		account.setEmail(accountUpdateForm.getEmail());
		account.setNumberPhone(accountUpdateForm.getNumberPhone());
		account.setGender(accountUpdateForm.getGender());
		account.setUpdateBy(Common.getUsernameLogin());
		account.setUpdateAt(Common.getSystemDate());
		account.setDescription(accountUpdateForm.getDescription());
		account.setCreateAt(Common.getSystemDate());
		account.setCreateBy(Common.getUsernameLogin());
		if (accountsRespository.save(account) == null) {
			return false;
		} else {
			return true;
		}
	}

    public Integer countAccount() {
        return accountsRespository.countAccount();
    }
}
