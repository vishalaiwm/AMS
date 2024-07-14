package com.org.customer.model;

import java.util.List;

public class CustomerSyncResponse {

    private List<CustomerMaster> customers;

    public List<CustomerMaster> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerMaster> customers) {
        this.customers = customers;
    }
}
