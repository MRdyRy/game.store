package com.rudy.ryanto.customer.login.controller;

import com.rudy.ryanto.customer.login.domain.ResponseLogin;
import com.rudy.ryanto.customer.login.service.CustomerLoginService;
import com.rudy.ryanto.user.management.domain.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CustomerLoginController {

    @Autowired
    CustomerLoginService customerLoginService;

    @PostMapping(value = "/v1/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLogin> signup(@RequestBody CustomerDto customerDto) {
        var response = customerLoginService.createNewUser(customerDto);
        return ResponseEntity.ok(ResponseLogin.builder()
                .statusCode(String.valueOf(response.getStatusCode().value()))
                .desc(response.getStatusCode().getReasonPhrase())
                .data(response.getBody())
                .build());
    }

    @PostMapping(value = "/v1/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseLogin> login(@RequestBody CustomerDto customerDto) {
        var response = customerLoginService.createNewUser(customerDto);
        return ResponseEntity.ok(ResponseLogin.builder()
                .statusCode(String.valueOf(response.getStatusCode().value()))
                .desc(response.getStatusCode().getReasonPhrase())
                .data(response.getBody())
                .build());
    }

}