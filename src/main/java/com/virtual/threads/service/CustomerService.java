package com.virtual.threads.service;

import com.virtual.threads.entity.Customer;
import com.virtual.threads.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer createCustomerDetail(Customer customer) {
        return repository.save(customer);
    }
}
