package com.org.customer.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.customer.dao.CustomerDao;
import com.org.customer.dto.Req.CustomerReqDto;
import com.org.customer.model.CustomerMaster;
import com.org.customer.model.CustomerSyncResponse;
import com.org.customer.service.CustomerService;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RestTemplate restTemplate;

    // Create Customer
    @Override
    public Boolean createCustomer(CustomerReqDto customerReqDto) {
        try {
            CustomerMaster customer = new CustomerMaster();
            customer.setFirstName(customerReqDto.getFirstName());
            customer.setLastName(customerReqDto.getLastName());
            customer.setStreet(customerReqDto.getStreet());
            customer.setAddress(customerReqDto.getAddress());
            customer.setCity(customerReqDto.getCity());
            customer.setState(customerReqDto.getState());
            customer.setEmail(customerReqDto.getEmail());
            customer.setPhone(customerReqDto.getPhone());
            customer.setPassword(customerReqDto.getPassword());
            customerDao.save(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Login Customer
    @Override
    public CustomerMaster loginCustomer(String email, String password) {
        Optional<CustomerMaster> customer = customerDao.findByEmailAndPassword(email, password);
        return customer.orElse(null);
    }

    // Update Customer
    @Override
    public Boolean updateCustomer(CustomerReqDto customerReqDto) {
        try {
            Optional<CustomerMaster> existingCustomerOpt = customerDao.findById(customerReqDto.getCustomerId());
            if (existingCustomerOpt.isPresent()) {
                CustomerMaster existingCustomer = existingCustomerOpt.get();
                existingCustomer.setFirstName(customerReqDto.getFirstName());
                existingCustomer.setLastName(customerReqDto.getLastName());
                existingCustomer.setStreet(customerReqDto.getStreet());
                existingCustomer.setAddress(customerReqDto.getAddress());
                existingCustomer.setCity(customerReqDto.getCity());
                existingCustomer.setState(customerReqDto.getState());
                existingCustomer.setEmail(customerReqDto.getEmail());
                existingCustomer.setPhone(customerReqDto.getPhone());
                existingCustomer.setPassword(customerReqDto.getPassword());
                customerDao.save(existingCustomer);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Delete Customer
    @Override
    public Boolean deleteCustomer(Integer customerId) {
        try {
            customerDao.deleteById(customerId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get All Customers
    @Override
    public List<CustomerMaster> getAllCustomers() {
        return customerDao.findAll();
    }

    // Get Customer By ID
    @Override
    public CustomerMaster getCustomerById(Integer customerId) {
        Optional<CustomerMaster> customer = customerDao.findById(customerId);
        return customer.orElse(null);
    }

    // Search Customers
    @Override
    public List<CustomerMaster> searchCustomers(String firstName, String city, String phone) {
        // Implement the search logic here based on the criteria
        // This is a placeholder implementation, you need to customize it
        if (firstName != null) {
            return customerDao.findByFirstNameContaining(firstName);
        } else if (city != null) {
            return customerDao.findByCityContaining(city);
        } else if (phone != null) {
            return customerDao.findByPhoneContaining(phone);
        } else {
            return customerDao.findAll();
        }
    }

    // Sync Customers
    @SuppressWarnings("null")
    @Override
    public boolean syncCustomers() {
        try {
            // Call remote API to fetch customer data
            ResponseEntity<CustomerSyncResponse> responseEntity = restTemplate.exchange(
                    "http://localhost:1036/customer/sync", HttpMethod.GET, null,
                    CustomerSyncResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<CustomerMaster> remoteCustomers = responseEntity.getBody().getCustomers();

                for (CustomerMaster remoteCustomer : remoteCustomers) {
                    // Check if customer already exists in database
                    Optional<CustomerMaster> existingCustomerOpt = customerDao.findById(remoteCustomer.getCustomerId());
                    if (existingCustomerOpt.isPresent()) {
                        // Update existing customer
                        CustomerMaster existingCustomer = existingCustomerOpt.get();
                        existingCustomer.setFirstName(remoteCustomer.getFirstName());
                        existingCustomer.setLastName(remoteCustomer.getLastName());
                        existingCustomer.setStreet(remoteCustomer.getStreet());
                        existingCustomer.setAddress(remoteCustomer.getAddress());
                        existingCustomer.setCity(remoteCustomer.getCity());
                        existingCustomer.setState(remoteCustomer.getState());
                        existingCustomer.setEmail(remoteCustomer.getEmail());
                        existingCustomer.setPhone(remoteCustomer.getPhone());
                        existingCustomer.setPassword(remoteCustomer.getPassword());
                        customerDao.save(existingCustomer);
                    } else {
                        // Save new customer
                        customerDao.save(remoteCustomer);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
