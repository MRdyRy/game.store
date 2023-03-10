package com.rudy.ryanto.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.ryanto.payment.helper.PaymentConstant;
import com.rudy.ryanto.payment.dto.PaymentStatus;
import com.rudy.ryanto.payment.dto.TransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ListenerPayment {

    @Autowired
    PublisherPayment publisherPayment;

    @Autowired
    RestTemplate restTemplate;

    @Value("${transaction.management.url:http://localhost:9010/api")
    private String hostTransactionUrl;

    @Value("${transaction.management.endpoint:/history")
    private String endpointHistory;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-submission", groupId = "game")
    public void listenerPayment(String message){
        log.info("message receive : {}",message);
        try{
            String url = hostTransactionUrl.concat(endpointHistory);
            log.info("call : {}",url);
            TransactionRequest transactionRequest = objectMapper.readValue(message,TransactionRequest.class);
            var response = restTemplate.postForEntity(url,transactionRequest,TransactionRequest.class);
            log.info("response : {}", response.getBody());
            if(response.getStatusCode().compareTo(HttpStatus.OK)==0){
                if(response.getBody().getStatusPayment()== PaymentConstant.SUCCESS){
                    publisherPayment.sendPaymentStatus(PaymentStatus.builder()
                            .statusPayment(response.getBody().getStatusPayment())
                            .transactionId(response.getBody().getIdCart())
                            .build());
                }
            }

        }catch (Exception e){

        }
    }
}
