package com.ktgroup.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long id;
	@Column
	private String name;
	@Column
	private String numberPhone;
	@Column
	private String description;
	@Column
	private int status;
	@Column
	private Date dateRecharge;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private AppUser appUser;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "customer")
	private List<CustomerHistory> listHistory;
	@Column
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
