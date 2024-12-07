package com.logonedigital.gestion_stock_g7.controller;

import com.logonedigital.gestion_stock_g7.dto.customer.CustomerReqDTO;
import com.logonedigital.gestion_stock_g7.dto.customer.CustomerResDTO;
import com.logonedigital.gestion_stock_g7.entities.Customer;
import com.logonedigital.gestion_stock_g7.services.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<CustomerResDTO> addCustomer(@Valid @RequestBody CustomerReqDTO customerReqDTO){

        return ResponseEntity
                .status(201)
                .body(this.customerService.addCustomer(customerReqDTO));
    }

    @GetMapping(path = "customer/get_all/{offset}/{pageSize}")
    public ResponseEntity<Page<CustomerResDTO>> getAllCustomer(@PathVariable int offset, @PathVariable int pageSize){

        return ResponseEntity
                .status(200)
                .body(this.customerService.getCustomers(offset,pageSize));
    }

    @GetMapping(path = "customers/get_by_id/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId){

        return ResponseEntity
                .status(200)
                .body(this.customerService.getCustomerById(customerId));
    }

    @GetMapping(path = "customers/get_by_email{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){

        return ResponseEntity
                .status(200)
                .body(this.customerService.getCustomerByEmail(email));
    }

    @PutMapping(path = "customers/update_by_id/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Integer customerId, @Valid @RequestBody Customer customer){

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
