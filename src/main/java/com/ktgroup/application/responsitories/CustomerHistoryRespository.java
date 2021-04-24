package com.ktgroup.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Customer;
import com.ktgroup.application.entities.CustomerHistory;

@Repository
public interface CustomerHistoryRespository extends JpaRepository<CustomerHistory, Long>{
	
	public List<CustomerHistory> findByCustomer(Customer customer);
}
