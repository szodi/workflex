package com.szodi.workflex.service;

import com.szodi.workflex.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Profile("!dev")
public class InitService implements ApplicationRunner {

    private final CustomerService customerService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        customerService.createCustomer(CustomerDto.builder()
                .email("sandor.szodoray@gmail.com")
                .name("Sandor Szodoray")
                .city("Miskolc")
                .addressLine1("Szikla u.")
                .addressLine2("2/B")
                .zipCode("3535")
                .build()
        );
    }
}
