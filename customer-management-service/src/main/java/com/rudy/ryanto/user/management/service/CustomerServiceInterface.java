package com.rudy.ryanto.user.management.service;

import com.rudy.ryanto.user.management.domain.Customer;
import com.rudy.ryanto.user.management.domain.CustomerDto;

import java.text.ParseException;
import java.util.List;

public interface CustomerServiceInterface {

    Customer doSaveNewCustomer(CustomerDto customer) throws ParseException;

    List<Customer> getCustomerByName(String name);

    Customer getCustomerById(Long id);
}
