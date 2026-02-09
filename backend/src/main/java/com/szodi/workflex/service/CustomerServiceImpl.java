package com.szodi.workflex.service;

import com.szodi.workflex.dto.CustomerDto;
import com.szodi.workflex.exception.CustomerNotFoundException;
import com.szodi.workflex.exception.MovieNotFoundException;
import com.szodi.workflex.mapper.CustomerMapper;
import com.szodi.workflex.model.Customer;
import com.szodi.workflex.model.Movie;
import com.szodi.workflex.repository.CustomerRepository;
import com.szodi.workflex.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MovieRepository movieRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getCustomerList() {
        return customerRepository.findAll().stream().map(customerMapper::convert).toList();
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .city(customerDto.getCity())
                .addressLine1(customerDto.getAddressLine1())
                .addressLine2(customerDto.getAddressLine2())
                .zipCode(customerDto.getZipCode())
                .build();
        customerRepository.save(customer);
        return customerMapper.convert(customer);
    }

    @Override
    public CustomerDto getCustomerDetails(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        return customerMapper.convert(customer);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        if (customerDto.getId() == null) {
            throw new CustomerNotFoundException();
        }
        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(CustomerNotFoundException::new);

        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setCity(customerDto.getCity());
        customer.setAddressLine1(customerDto.getAddressLine1());
        customer.setAddressLine2(customerDto.getAddressLine2());
        customer.setZipCode(customerDto.getZipCode());

        customerRepository.save(customer);;
        return customerMapper.convert(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    @Transactional
    public void rentMovie(Long customerId, Long movieId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
        customer.getMovies().add(movie);
        customerRepository.save(customer);
    }
}
