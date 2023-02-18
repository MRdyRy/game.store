package com.rudy.ryanto.payment;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerPayment {

    @KafkaListener(topics = "payment-game")
    public void listenerPayment(){

    }
}
