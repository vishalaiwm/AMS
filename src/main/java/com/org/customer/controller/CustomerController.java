package com.org.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.org.customer.dto.Req.CustomerReqDto;
import com.org.customer.model.CustomerMaster;
import com.org.customer.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create Customer
    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> createCustomer(@RequestBody CustomerReqDto customerReqDto) {
        Boolean flag = customerService.createCustomer(customerReqDto);
        if (flag) {
            return new ResponseEntity<>(flag, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Login Customer
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginCustomer(@RequestBody CustomerReqDto customerReqDto) {
        CustomerMaster customer = customerService.loginCustomer(customerReqDto.getEmail(),
                customerReqDto.getPassword());
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Update Customer
    @PutMapping(value = "/update")
    public ResponseEntity<Boolean> updateCustomer(@RequestBody CustomerReqDto customerReqDto) {
        Boolean flag = customerService.updateCustomer(customerReqDto);
        if (flag) {
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Customer
    @DeleteMapping(value = "/delete/{customerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable Integer customerId) {
        Boolean flag = customerService.deleteCustomer(customerId);
        if (flag) {
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(flag, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Customers
    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity<List<CustomerMaster>> getAllCustomers() {
        List<CustomerMaster> list = customerService.getAllCustomers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Get Customer By ID
    @GetMapping(value = "/getCustomerById/{customerId}")
    public ResponseEntity<CustomerMaster> getCustomerById(@PathVariable Integer customerId) {
        CustomerMaster customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Search Customers
    @GetMapping(value = "/search")
    public ResponseEntity<List<CustomerMaster>> searchCustomers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "phone", required = false) String phone) {

        List<CustomerMaster> list = customerService.searchCustomers(firstName, city, phone);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/sync")
    public ResponseEntity<String> syncCustomers() {
        boolean result = customerService.syncCustomers(); // Implement this method in service
        if (result) {
            return new ResponseEntity<>("Customers synchronized successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to sync customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
