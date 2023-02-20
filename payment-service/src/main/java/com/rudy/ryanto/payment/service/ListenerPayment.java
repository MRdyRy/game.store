package com.rudy.ryanto.payment.service;

import com.rudy.ryanto.payment.Util.PaymentConstant;
import com.rudy.ryanto.payment.domain.PaymentStatus;
import com.rudy.ryanto.payment.domain.TransactionRequest;
import com.rudy.ryanto.report.domain.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
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

    @KafkaListener(topics = "payment-submission")
    public void listenerPayment(TransactionRequest transactionRequest){
        try{
            String url = hostTransactionUrl.concat(endpointHistory);
            log.info("call : {}",url);
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
