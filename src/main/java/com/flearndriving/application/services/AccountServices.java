package com.flearndriving.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.flearndriving.application.common.AuthenticationProvider;
import com.flearndriving.application.common.Common;
import com.flearndriving.application.common.Constant;
import com.flearndriving.application.dto.AccountForm;
import com.flearndriving.application.dto.AccountPrincipal;
import com.flearndriving.application.dto.AccountUpdateForm;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Role;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.AccountsRespository;
import com.flearndriving.application.responsitories.RoleRespository;
import com.flearndriving.application.utils.EncrytedPasswordUtils;

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
		account.setAuthProvider(AuthenticationProvider.GOOGLE.toString());
		account.setEncrytedPassword(StringUtils.EMPTY);
		Role role = roleRespository.getOne(Constant.ROLE_ID_USER);
		account.setRole(role);
		accountsRespository.save(account);
	}

	@Transactional
	public void updateAccountAfterOAuthLoginSuccess(Account account, String authenticationProvider) {
		account.setAuthProvider(AuthenticationProvider.GOOGLE.toString());
		accountsRespository.save(account);
	}

	@Transactional
	public void updateAccount(AccountUpdateForm accountUpdateForm) {
		if (accountUpdateForm == null) {
		    throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Dữ liệu truền vào không hợp lệ!");
		}
		Account account = accountsRespository.findByAccountId(accountUpdateForm.getAccountId());
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
		account.setAccountId(accountUpdateForm.getAccountId());
		account.setFirstName(accountUpdateForm.getFirstName());
		account.setMiddleName(accountUpdateForm.getMiddleName());
		account.setLastName(accountUpdateForm.getLastName());
		account.setBirthDay(Common.stringToDate(accountUpdateForm.getBirthDay()));
		account.setEmail(accountUpdateForm.getEmail());
		account.setNumberPhone(accountUpdateForm.getNumberPhone());
		account.setGender(accountUpdateForm.getGender());
		account.setDescription(accountUpdateForm.getDescription());
		account.setUpdateBy(Common.getUsernameLogin());
		account.setUpdateAt(Common.getSystemDate());
		account.setCreateAt(Common.getSystemDate());
		account.setCreateBy(Common.getUsernameLogin());
		accountsRespository.save(account);
	}

	public Object getAccountLoginInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
		AccountForm accountForm = new AccountForm();
		Account account = accountsRespository.findByEmail(loginedUser.getEmail());
		accountForm.setAccountId(account.getAccountId());
		accountForm.setFirstName(account.getFirstName());
		accountForm.setMiddleName(account.getMiddleName());
		accountForm.setLastName(account.getLastName());
		accountForm.setUserName(account.getUserName());
		accountForm.setBirthDay(DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
		accountForm.setNumberPhone(account.getNumberPhone());
		accountForm.setEmail(account.getEmail());
		accountForm.setGender(account.getGender());
		accountForm.setRoleId(account.getRole().getRoleId());
		accountForm.setCreateAt(Common.getSystemDate());
		accountForm.setCreateBy(Common.getUsernameLogin());
		accountForm.setUpdateAt(Common.getSystemDate());
		accountForm.setUpdateBy(Common.getUsernameLogin());
		accountForm.setAddress(account.getAddress());
		accountForm.setWard(account.getWard());
		accountForm.setDescription(account.getDescription());
		return accountForm;
	}

	public Integer countAccount() {
		return accountsRespository.countAccount();
	}
}
