package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.dto.customer.CustomerReqDTO;
import com.logonedigital.gestion_stock_g7.entities.Customer;
import org.springframework.data.domain.Page;



public interface CustomerService {
    Customer addCustomer(CustomerReqDTO customerReqDTO);
    Page<Customer> getCustomers(int offset, int pageSize);
    Customer getCustomerById(Integer customerId);
    Customer getCustomerByEmail(String email);
    Customer updatedCustomer(Customer customer, Integer customerId);
    void deleteCustomer(Integer customerId);
}
