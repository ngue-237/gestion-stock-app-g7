package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.dto.customer.CustomerReqDTO;
import com.logonedigital.gestion_stock_g7.dto.customer.CustomerResDTO;
import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.entities.Location;
import com.logonedigital.gestion_stock_g7.exception.ResourceExistException;
import com.logonedigital.gestion_stock_g7.exception.ResourceNotFoundException;
import com.logonedigital.gestion_stock_g7.mapper.CustomerMapper;
import com.logonedigital.gestion_stock_g7.repositories.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepo customerRepo;
    private final LocationService locationService;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, LocationService locationService, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.locationService = locationService;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerResDTO addCustomer(CustomerReqDTO customerReqDTO) {

        Optional<Customer> customerExist = this.customerRepo
                .fetchByEmail(customerReqDTO.getEmail());
        if(customerExist.isPresent())
            throw new ResourceExistException("This email already exist !");

        Customer customer = this.customerMapper.fromCustomerReqDTO(customerReqDTO);

        customer.setCreatedAt(new Date());
        customer.setStatus(true);
        Location location = this.customerMapper.fromLocationReqDTO(customerReqDTO.getLocationReqDTO());

        customer.setLocation(this.locationService.addLocation((location)));
        return this.customerMapper.fromCustomer(this.customerRepo.save(customer));
    }

    @Override
    public Page<CustomerResDTO> getCustomers(int offset, int pageSize) {


        return this.customerRepo.findAll(PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "createdAt")))
                        .map(customer -> this.customerMapper.fromCustomer(customer));
    }

    @Override
    public Customer getCustomerById(Integer customerId) {

        return this.customerRepo.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer not found !")
        );
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        //TODO: corriger ce bug
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
