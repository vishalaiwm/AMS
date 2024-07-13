package com.org.customer.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.customer.service.*;
import com.org.customer.dao.CustomerDao;
import com.org.customer.dto.Req.CustomerReqDto;
import com.org.customer.model.CustomerMaster;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Boolean createCustomer(CustomerReqDto customerReqDto) {
        CustomerMaster customerMaster = new CustomerMaster();
        BeanUtils.copyProperties(customerReqDto, customerMaster);
        try {
            customerDao.save(customerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateCustomer(CustomerReqDto customerReqDto) {
        CustomerMaster customerMaster = new CustomerMaster();
        customerMaster.setCustomerId(customerReqDto.getCustomerId());
        BeanUtils.copyProperties(customerReqDto, customerMaster);
        try {
            customerDao.save(customerMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteCustomer(Integer customerId) {
        try {
            customerDao.deleteById(customerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CustomerMaster> getAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public CustomerMaster getCustomerById(Integer customerId) {
        Optional<CustomerMaster> customerMaster = customerDao.findById(customerId);
        return customerMaster.orElse(null);
    }

    @Override
    public CustomerMaster loginCustomer(String email, String password) {
        Optional<CustomerMaster> customer = customerDao.findByEmailAndPassword(email, password);
        return customer.orElse(null);
    }

    @Override
    public List<CustomerMaster> searchCustomers(String firstName, String city, String phone) {
        List<CustomerMaster> customers = customerDao.findAll();

        return customers.stream()
                .filter(customer -> (firstName == null || customer.getFirstName().contains(firstName)) &&
                        (city == null || customer.getCity().contains(city)) &&
                        (phone == null || customer.getPhone().contains(phone)))
                .collect(Collectors.toList());
    }
}
