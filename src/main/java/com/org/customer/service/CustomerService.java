package com.org.customer.service;

import java.util.List;

import com.org.customer.dto.Req.CustomerReqDto;
import com.org.customer.model.CustomerMaster;

public interface CustomerService {
	Boolean createCustomer(CustomerReqDto customerReqDto);

	CustomerMaster loginCustomer(String email, String password);

	Boolean updateCustomer(CustomerReqDto customerReqDto);

	Boolean deleteCustomer(Integer customerId);

	List<CustomerMaster> getAllCustomers();

	CustomerMaster getCustomerById(Integer customerId);

	List<CustomerMaster> searchCustomers(String firstName, String city, String phone);

	boolean syncCustomers();
}
