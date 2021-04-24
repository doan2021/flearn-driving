/**
 * 
 */
package com.ktgroup.application.entities;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "App_User", uniqueConstraints = { @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String gender;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(columnDefinition = "boolean default false")
    private boolean isDelete;

    @Column
    private String createAt;

    @Column
    private Date createDate;

    @Column
    private String updateAt;

    @Column
    private Date updateDate;
}
