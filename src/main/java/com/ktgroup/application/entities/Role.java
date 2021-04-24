package com.ktgroup.application.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Role")
@Getter
@Setter
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "role_id")
	private Long roleId;
	@Column
	private String roleName;
	
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
