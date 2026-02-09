package com.szodi.workflex.controller;

import com.szodi.workflex.dto.CustomerDto;
import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.service.CustomerService;
import com.szodi.workflex.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getCustomerList() {
        return ResponseEntity.ok(customerService.getCustomerList());
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @PutMapping("/")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.updateCustomer(customerDto));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerDetails(customerId));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/{customerId}/{movieId}")
    public void rentMovie(@PathVariable Long customerId, @PathVariable Long movieId) {
        customerService.rentMovie(customerId, movieId);
    }
}
