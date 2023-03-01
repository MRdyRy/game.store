package com.rudy.ryanto.game.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rudy.ryanto.game.product.dto.GameDto;
import com.rudy.ryanto.game.product.entity.GameProduct;
import com.rudy.ryanto.game.product.service.GameProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game/product")
public class GameProductController {

    @Autowired
    GameProductService gameProductService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/detail")
    public GameProduct getDetailGame(@RequestBody GameDto gameDto){
        return gameProductService.getDetailGameProduct(gameDto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/purchase")
    public void sendPurchase(@RequestBody GameDto gameDto) throws JsonProcessingException {
        gameProductService.purchase(gameDto);
    }


}
