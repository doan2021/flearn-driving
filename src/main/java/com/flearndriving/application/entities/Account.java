/**
 * 
 */
package com.flearndriving.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
/**
 * @author tamdu
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(name = "ACCOUNTS_UK", columnNames = "username") })
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "username", length = 36)
    private String userName;

    @Column(name = "encryted_password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone", length = 10)
    private String numberPhone;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = true)
    private Ward ward;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "auth_provider", length = 15)
    private String authProvider;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Document> listImages;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<ExamProfile> listExamProfile;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TrialExamResult> listTrialExamResult;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<StatusLearn> listStatusLearn;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Document> getListImages() {
        return listImages;
    }

    public void setListImages(List<Document> listImages) {
        this.listImages = listImages;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public List<ExamProfile> getListExamProfile() {
        return listExamProfile;
    }

    public void setListExamProfile(List<ExamProfile> listExamProfile) {
        this.listExamProfile = listExamProfile;
    }

    public List<TrialExamResult> getListTrialExamResult() {
        return listTrialExamResult;
    }

    public void setListTrialExamResult(List<TrialExamResult> listTrialExamResult) {
        this.listTrialExamResult = listTrialExamResult;
    }

    public List<StatusLearn> getListStatusLearn() {
        return listStatusLearn;
    }

    public void setListStatusLearn(List<StatusLearn> listStatusLearn) {
        this.listStatusLearn = listStatusLearn;
    }

}
