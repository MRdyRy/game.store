package com.rudy.ryanto.game.product.util;

import com.rudy.ryanto.game.product.domain.GameDto;
import com.rudy.ryanto.game.product.domain.GameProduct;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperGameDtoToGameProduct {

    public List<GameProduct> mapGameDtoToGameProduct(List<GameDto> dtoList){
        List<GameProduct> list = dtoList.stream().map(dto-> GameProduct.builder()
                .gameId(dto.getGameID())
                .price(Double.parseDouble(dto.getSalePrice()))
                .thumb(dto.getThumb())
                .title(dto.getTitle())
                .steamRatingCount(dto.getSteamRatingCount())
                .steamRatingPercent(dto.getSteamRatingPercent())
                .steamRatingText(dto.getSteamRatingText())
                .storeId(dto.getStoreID())
                .createBy("SYSTEM")
                .createDate(new Date())
                .build()).collect(Collectors.toList());

        return list;
    }

}
