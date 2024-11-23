package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> getCustomers();
    Customer getCustomerById(Integer customerId);
    Customer getCustomerByEmail(String email);
    Customer updatedCustomer(Customer customer, Integer customerId);
    void deleteCustomer(Integer customerId);
}
