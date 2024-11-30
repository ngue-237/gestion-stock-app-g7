package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.exception.ResourceExistException;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepo customerRepo;
    private final LocationService locationService;

    public CustomerServiceImpl(CustomerRepo customerRepo, LocationService locationService) {
        this.customerRepo = customerRepo;
        this.locationService = locationService;
    }


    @Override
    public Customer addCustomer(Customer customer) {

        Optional<Customer> customerExist = this.customerRepo
                .fetchByEmail(customer.getEmail());
        if(customerExist.isPresent())
            throw new ResourceExistException("This email already exist !");
        customer.setCreatedAt(new Date());
        customer.setStatus(true);
        customer.setLocation(this.locationService.addLocation((customer.getLocation())));
        return this.customerRepo.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(Integer customerId) {

        return this.customerRepo.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer not found !")
        );
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return this.customerRepo.fetchByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found !"));
    }

    @Override
    public Customer updatedCustomer(Customer customer, Integer customerId) {
        //Rechercher le client
        Optional<Customer> customerToEdit = this.customerRepo.findById(customerId);
        if(customerToEdit.isEmpty())
            throw new ResourceNotFoundException("Customer not found !");
        //Modidier les informations du clients
        if(customer.getFirstname()!=null)
            customerToEdit.get().setFirstname(customer.getFirstname());
        if(customer.getLastname()!=null)
            customerToEdit.get().setLastname(customer.getLastname());
        if(customer.getPhone()!=null)
            customerToEdit.get().setPhone(customer.getPhone());
        if(customer.getEmail()!=null)
            customerToEdit.get().setEmail(customer.getEmail());
        customerToEdit.get().setUpdatedAt(new Date());
        //Sauvegarder les modifications
        return this.customerRepo.saveAndFlush(customerToEdit.get());
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer not found"));
        this.customerRepo.delete(customer);

    }
}
