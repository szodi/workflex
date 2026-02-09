package com.szodi.workflex.service;

import com.szodi.workflex.dto.CustomerDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getCustomerList();
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerDetails(Long customerId);
    CustomerDto updateCustomer(CustomerDto customerDto);
    void deleteCustomer(Long customerId);
    void rentMovie(Long customerId, Long movieId);
}
