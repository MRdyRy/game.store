package com.rudy.ryanto.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.ryanto.payment.dto.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherPayment {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${topic.name.payment.status:payment-status}")
    private String topicNamePaymentStatus;

    public void sendPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException {
        log.info("send message to topic : {} message : {}",topicNamePaymentStatus,paymentStatus);
        kafkaTemplate.send(topicNamePaymentStatus,objectMapper.writeValueAsString(paymentStatus));
    }
}
