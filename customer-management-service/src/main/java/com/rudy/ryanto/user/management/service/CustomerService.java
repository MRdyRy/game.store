package com.rudy.ryanto.user.management.service;

import com.rudy.ryanto.user.management.Repository.CustomerRepository;
import com.rudy.ryanto.user.management.exception.CustomerException;
import com.rudy.ryanto.user.management.dto.Customer;
import com.rudy.ryanto.user.management.dto.CustomerDto;
import com.rudy.ryanto.user.management.util.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService implements CustomerServiceInterface{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Customer doSaveNewCustomer(CustomerDto customer) throws ParseException {
        log.info("save new customer : {}",customer);
        var newCustomer = customerRepository.save(customerMapper.mappingDtoToCustomer(customer));
        return newCustomer;
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        log.info("get customer by name : {}", name);
        var customers = customerRepository.findAllByCustomerNameLike(name);
        var listActiveCustomer = customers.stream()
                .filter(c-> c.getStatusActive().equals(Boolean.TRUE))
                .collect(Collectors.toList());
        return listActiveCustomer;
    }

    @Override
    public Customer getCustomerById(Long id) {
        log.info("get customer by id : {}",id);
        Optional<Customer> customer1 = customerRepository.findById(id);
        if(customer1.isPresent()){
            return customer1.get();
        }else{
            throw new CustomerException("Customer not found!");
        }
    }
}
