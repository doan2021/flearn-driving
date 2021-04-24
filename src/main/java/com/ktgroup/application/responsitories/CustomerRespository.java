package com.ktgroup.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ktgroup.application.entities.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {
}
