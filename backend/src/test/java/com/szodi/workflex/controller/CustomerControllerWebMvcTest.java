package com.szodi.workflex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szodi.workflex.dto.CustomerDto;
import com.szodi.workflex.dto.MovieDto;
import com.szodi.workflex.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerWebMvcTest {

    @Autowired MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    CustomerService customerService;

    @Test
    void getCustomerList_returns200AndJsonArray() throws Exception {
        CustomerDto c1 = CustomerDto.builder()
                .id(1L).email("a@b.com").name("Alice")
                .city("Paris").addressLine1("Line1").addressLine2("Line2").zipCode("75001")
                .movies(Set.of())
                .build();

        CustomerDto c2 = CustomerDto.builder()
                .id(2L).email("c@d.com").name("Bob")
                .city("Lyon").addressLine1("Addr1").addressLine2(null).zipCode("69000")
                .movies(Set.of())
                .build();

        when(customerService.getCustomerList()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/api/customers/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("a@b.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bob"));

        verify(customerService).getCustomerList();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void createCustomer_postsBody_returns200AndCreatedDto() throws Exception {
        CustomerDto request = CustomerDto.builder()
                .email("new@x.com").name("New Name")
                .city("Nice").addressLine1("A1").addressLine2("A2").zipCode("06000")
                .movies(Set.of())
                .build();

        CustomerDto response = CustomerDto.builder()
                .id(10L)
                .email("new@x.com").name("New Name")
                .city("Nice").addressLine1("A1").addressLine2("A2").zipCode("06000")
                .movies(Set.of())
                .build();

        when(customerService.createCustomer(ArgumentMatchers.any(CustomerDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.email").value("new@x.com"))
                .andExpect(jsonPath("$.name").value("New Name"));

        verify(customerService).createCustomer(ArgumentMatchers.any(CustomerDto.class));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void updateCustomer_putsBody_returns200AndUpdatedDto() throws Exception {
        CustomerDto request = CustomerDto.builder()
                .id(5L).email("u@x.com").name("Updated")
                .city("Marseille").addressLine1("L1").addressLine2("L2").zipCode("13000")
                .movies(Set.of())
                .build();

        CustomerDto response = CustomerDto.builder()
                .id(5L).email("u@x.com").name("Updated")
                .city("Marseille").addressLine1("L1").addressLine2("L2").zipCode("13000")
                .movies(Set.of(MovieDto.builder().id(99L).title("Inception").genre("Sci-Fi").imdbRating("8.8").build()))
                .build();

        when(customerService.updateCustomer(ArgumentMatchers.any(CustomerDto.class))).thenReturn(response);

        mockMvc.perform(put("/api/customers/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Updated"))
                .andExpect(jsonPath("$.movies").isArray());

        verify(customerService).updateCustomer(ArgumentMatchers.any(CustomerDto.class));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void getCustomerDetails_returns200AndCustomer() throws Exception {
        CustomerDto response = CustomerDto.builder()
                .id(42L).email("x@y.com").name("Detail")
                .city("Paris").addressLine1("Rue 1").addressLine2("").zipCode("75002")
                .movies(Set.of())
                .build();

        when(customerService.getCustomerDetails(42L)).thenReturn(response);

        mockMvc.perform(get("/api/customers/{customerId}", 42L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(42))
                .andExpect(jsonPath("$.email").value("x@y.com"))
                .andExpect(jsonPath("$.name").value("Detail"));

        verify(customerService).getCustomerDetails(42L);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void deleteCustomer_returns200AndDelegates() throws Exception {
        mockMvc.perform(delete("/api/customers/{customerId}", 7L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(customerService).deleteCustomer(7L);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void rentMovie_returns200AndDelegates() throws Exception {
        mockMvc.perform(put("/api/customers/{customerId}/{movieId}", 7L, 99L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(customerService).rentMovie(7L, 99L);
        verifyNoMoreInteractions(customerService);
    }
}
