package com.ktgroup.application.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerHistoryId;
	@Column
	private String content;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer")
	private Customer customer;
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
