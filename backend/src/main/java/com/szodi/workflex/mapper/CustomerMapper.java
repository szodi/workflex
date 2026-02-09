package com.szodi.workflex.mapper;

import com.szodi.workflex.dto.CustomerDto;
import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.model.Customer;
import com.szodi.workflex.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final MovieMapper movieMapper;

    public CustomerDto convert(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .name(customer.getName())
                .city(customer.getCity())
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .zipCode(customer.getZipCode())
                .movies(
                        customer.getMovies() != null ? customer.getMovies().stream()
                                .map(movieMapper::convert).collect(Collectors.toSet()) : null)
                .build();
    }
}
