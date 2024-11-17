package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @Override
    public Customer addCustomer(Customer customer) {
        customer.setCreatedAt(new Date());
        customer.setStatus(true);
        return this.customerRepo.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return this.customerRepo.findById(customerId).get();
    }

    @Override
    public Customer updatedCustomer(Customer customer, Integer customerId) {
        //Rechercher le client
        Customer customerToEdit = this.customerRepo.findById(customerId).get();
        //Modidier les informations du clients
        customerToEdit.setFirstname(customer.getFirstname());
        customerToEdit.setLastname(customer.getLastname());
        customerToEdit.setPhone(customer.getPhone());
        customerToEdit.setEmail(customer.getEmail());
        customerToEdit.setUpdatedAt(new Date());
        //Sauvegarder les modifications
        return this.customerRepo.saveAndFlush(customerToEdit);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        this.customerRepo.deleteById(customerId);

    }
}
