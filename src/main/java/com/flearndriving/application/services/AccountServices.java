package com.flearndriving.application.services;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flearndriving.application.common.Common;
import com.flearndriving.application.common.Constant;
import com.flearndriving.application.common.MimeTypes;
import com.flearndriving.application.dto.AccountForm;
import com.flearndriving.application.dto.AccountLogin;
import com.flearndriving.application.dto.AccountUpdateForm;
import com.flearndriving.application.dto.CustomOAuth2User;
import com.flearndriving.application.entities.Account;
import com.flearndriving.application.entities.Document;
import com.flearndriving.application.entities.Role;
import com.flearndriving.application.exception.BusinessException;
import com.flearndriving.application.responsitories.AccountsRespository;
import com.flearndriving.application.responsitories.DocumentRespository;
import com.flearndriving.application.responsitories.RoleRespository;
import com.flearndriving.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

    @Autowired
    AccountsRespository accountsRespository;

    @Autowired
    RoleRespository roleRespository;
    
    @Autowired
    DocumentRespository documentRespository;
    
    @Autowired
    AmazonS3ClientService amazonS3ClientService;

    public Account findByEmail(String email) {
        return accountsRespository.findByEmail(email);
    }

    @Transactional
    public void createAccount(AccountForm accountForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(accountForm.getPassword());
        Account account = new Account();
        account.setUserName(accountForm.getUserName());
        account.setFirstName(accountForm.getFirstName());
        account.setMiddleName(accountForm.getMiddleName());
        account.setLastName(accountForm.getLastName());
        account.setEmail(accountForm.getEmail());
        account.setGender(accountForm.getGender());
        account.setNumberPhone(accountForm.getNumberPhone());
        account.setBirthDay(Common.stringToDate(accountForm.getBirthDay()));
        account.setEncrytedPassword(encrytedPassword);
        Role role = roleRespository.getOne(Constant.ROLE_ID_USER);
        account.setRole(role);
        account.setCreateAt(Common.getSystemDate());
        account.setCreateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        accountsRespository.save(account);
    }

    public Account getAccountLogin() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return accountsRespository.findByUserName(userName);
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            return accountsRespository.findByEmail(oAuth2User.getEmail());
        }
        return null;
    }
    
    public AccountLogin getBasicInfoAccountLogin() {
        AccountLogin accountLogin = new AccountLogin();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            accountLogin = accountsRespository.findBasicInfoByUserName(userName);
        } else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            accountLogin = accountsRespository.findBasicInfoByEmail(oAuth2User.getEmail());
        }
        if (accountLogin != null) {
            accountLogin.setUrlAvater(documentRespository.findUrlDocumentByTypeAndAccountId(Constant.TYPE_DOCUMENT_AVATAR, accountLogin.getAccountId()));
        }
        return accountLogin;
    }

    @Transactional
    public void createAccountAfterOAuthLoginSuccess(String email, String fullName, String authenticationProvider) {
        Account account = new Account();
        account.setEmail(email);
        account.setFirstName(Common.getFirstName(fullName));
        account.setMiddleName(Common.getMiddleName(fullName));
        account.setLastName(Common.getLastName(fullName));
        account.setAuthProvider(authenticationProvider);
        account.setEncrytedPassword(StringUtils.EMPTY);
        Role role = roleRespository.getOne(Constant.ROLE_ID_USER);
        account.setRole(role);
        account.setCreateAt(Common.getSystemDate());
        account.setCreateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        accountsRespository.save(account);
    }

    @Transactional
    public void updateAccountAfterOAuthLoginSuccess(Account account, String authenticationProvider) {
        account.setAuthProvider(authenticationProvider);
        account.setCreateAt(Common.getSystemDate());
        account.setCreateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        accountsRespository.save(account);
    }

    @Transactional
    public void updateAccount(AccountUpdateForm accountUpdateForm) {
        if (accountUpdateForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Dữ liệu truền vào không hợp lệ!");
        }
        Account account = getAccountLogin();
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
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
        accountsRespository.save(account);
    }

    public AccountForm getAccountLoginInfo() {
        AccountForm accountForm = new AccountForm();
        Account account = getAccountLogin();
        accountForm.setAccountId(account.getAccountId());
        accountForm.setFirstName(account.getFirstName());
        accountForm.setMiddleName(account.getMiddleName());
        accountForm.setLastName(account.getLastName());
        accountForm.setUserName(account.getUserName());
        if (account.getBirthDay() != null) {
            accountForm.setBirthDay(DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
        }
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
    
    @Transactional
    public void uploadAvatar(MultipartFile file) {
        Account account = getAccountLogin();
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        if (file.isEmpty()) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "File không hợp lệ!");
        }
        Document document = documentRespository.findByTypeAndAccountId(Constant.TYPE_DOCUMENT_AVATAR, account.getAccountId());
        if (document != null) {
            document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
            document.setOriginFileName(file.getOriginalFilename());
            document.setExtension(MimeTypes.lookupExt(file.getContentType()));
            document.setContentType(file.getContentType());
            document.setSize(file.getSize());
            document.setType(Constant.TYPE_DOCUMENT_AVATAR);
            document.setDescription("Ảnh đại diện");
            document.setCreateAt(Common.getSystemDate());
            document.setCreateBy(Common.getUsernameLogin());
            amazonS3ClientService.deleteFileFromS3Bucket(document.getPath());
            document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            documentRespository.save(document);
        } else {
            document = new Document();
            document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
            document.setOriginFileName(file.getOriginalFilename());
            document.setExtension(MimeTypes.lookupExt(file.getContentType()));
            document.setContentType(file.getContentType());
            document.setSize(file.getSize());
            document.setType(Constant.TYPE_DOCUMENT_AVATAR);
            document.setDescription("Ảnh mô tả chương");
            document.setCreateAt(Common.getSystemDate());
            document.setCreateBy(Common.getUsernameLogin());
            document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            document.setAccount(account);
        }
        documentRespository.save(document);
    }
}
