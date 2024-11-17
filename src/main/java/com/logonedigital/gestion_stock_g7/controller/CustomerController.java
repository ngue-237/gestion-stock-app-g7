package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.services.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "customers/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){

        return ResponseEntity
                .status(201)
                .body(this.customerService.addCustomer(customer));
    }

    @GetMapping(path = "customer/get_all")
    public ResponseEntity<List<Customer>> getAllCustomer(){

        return ResponseEntity
                .status(200)
                .body(this.customerService.getCustomers());
    }

    @GetMapping(path = "customers/get_by_id/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId){

        return ResponseEntity
                .status(200)
                .body(this.customerService.getCustomerById(customerId));
    }

    @PutMapping(path = "customers/update_by_id/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Integer customerId, @RequestBody Customer customer){

        return ResponseEntity
                .status(202)
                .body(this.customerService.updatedCustomer(customer,customerId));
    }

    @DeleteMapping(path = "customers/delete_by_id/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId){
        this.customerService.deleteCustomer(customerId);
        return ResponseEntity
                .status(202)
                .body("Deleted successfully !");
    }
}
