package com.rudy.ryanto.user.management.controller;

import com.rudy.ryanto.user.management.service.CustomerServiceInterface;
import com.rudy.ryanto.user.management.dto.Customer;
import com.rudy.ryanto.user.management.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer saveNewCustomer(@RequestBody CustomerDto customerDto) throws ParseException {
        log.info("/api/save/customer");
        return customerServiceInterface.doSaveNewCustomer(customerDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/search/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findCustomerByName(@RequestBody CustomerDto customerDto) throws ParseException {
        log.info("/api/search/customers");
        return customerServiceInterface.getCustomerByName(customerDto.getCustomerName());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/search/customerbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer findCustomerById(@RequestBody String id) throws ParseException {
        log.info("/api/search/customerbyid");
        return customerServiceInterface.getCustomerById(Long.parseLong(id));
    }
}
