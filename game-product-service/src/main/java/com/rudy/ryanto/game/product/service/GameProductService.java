package com.rudy.ryanto.game.product.service;

import com.rudy.ryanto.game.product.dto.GameDto;
import com.rudy.ryanto.game.product.entity.GameProduct;
import com.rudy.ryanto.game.product.exception.GameProductException;
import com.rudy.ryanto.game.product.repository.GameProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameProductService {

    @Autowired
    GameProductRepository gameProductRepository;
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
}
