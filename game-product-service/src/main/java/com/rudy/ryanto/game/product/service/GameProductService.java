package com.rudy.ryanto.game.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.ryanto.game.product.dto.GameDto;
import com.rudy.ryanto.game.product.entity.GameProduct;
import com.rudy.ryanto.game.product.exception.GameProductException;
import com.rudy.ryanto.game.product.repository.GameProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class GameProductService {

    @Autowired
    GameProductRepository gameProductRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;

    private final String PAYMENT_SUBMISIION = "payment-submission";

    @Autowired
    ObjectMapper objectMapper;
    public GameProduct getDetailGameProduct(GameDto gameDto) {
        log.info("req : {}",gameDto);
        try{
            var o = gameProductRepository.findById(gameDto.getGameID());
            if(o.isPresent())
                return o.get();
            else
                return null;
        }catch (Exception e){
            throw new GameProductException(e.getMessage());
        }
    }

    public void purchase(GameDto gameDto) throws JsonProcessingException {
        log.info("send message to topic : {} message : {}",PAYMENT_SUBMISIION,gameDto);
        String message = objectMapper.writeValueAsString(gameDto);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(PAYMENT_SUBMISIION, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
