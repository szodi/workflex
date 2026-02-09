package com.szodi.workflex.dto;

import com.szodi.workflex.model.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    Long id;
    String email;
    String name;
    String city;
    String addressLine1;
    String addressLine2;
    String zipCode;
    Set<MovieDto> movies;
}
