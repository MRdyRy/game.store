package com.rudy.ryanto.customer.login.service;

import com.rudy.ryanto.customer.login.domain.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CustomerLoginService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RestTemplate restTemplate;

    @Value("${customer.management.host:localhost:9070/api/customer}")
    private String customerHostUrl;
    @Value("${signup.endpoint:/save}")
    private String createCustomerEndpoint;
    @Value("${signup.endpoint:/login}")
    private String loginEndpoint;

    public ResponseEntity<CustomerDto> createNewUser(CustomerDto customerDto) {
        ResponseEntity<CustomerDto> response = null;
        try{
            String url = customerHostUrl.concat(createCustomerEndpoint);
            log.info("call : {}",url);
            response = restTemplate.postForEntity(url,customerDto,CustomerDto.class);
            log.info("response : {}", response.getBody());
        }catch (Exception e){
            log.error("error call customer-management-service caused :",e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    public ResponseEntity<CustomerDto> login(CustomerDto customerDto) {
        ResponseEntity<CustomerDto> response = null;
        try{
            String url = customerHostUrl.concat(loginEndpoint);
            log.info("call : {}",url);
            response = restTemplate.postForEntity(url,customerDto,CustomerDto.class);
            log.info("response : {}", response.getBody());
            if(response.getStatusCode().compareTo(HttpStatus.OK)==0){
                redisTemplate.opsForHash().put("LOGIN",response.getBody().getCustomerName(),response.getBody());
            }
        }catch (Exception e){
            log.error("error call customer-management-service caused :",e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
}