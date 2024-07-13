package com.org.customer.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.customer.model.CustomerMaster;

public interface CustomerDao extends JpaRepository<CustomerMaster, Integer> {

	Optional<CustomerMaster> findByEmailAndPassword(String email, String password);

}
