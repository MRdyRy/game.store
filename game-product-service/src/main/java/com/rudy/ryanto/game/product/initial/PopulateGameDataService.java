package com.rudy.ryanto.game.product.initial;

import com.rudy.ryanto.game.product.domain.GameDto;
import com.rudy.ryanto.game.product.domain.GameProduct;
import com.rudy.ryanto.game.product.repository.GameProductRepository;
import com.rudy.ryanto.game.product.service.GameProductService;
import com.rudy.ryanto.game.product.util.MapperGameDtoToGameProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Order(1)
public class PopulateGameDataService implements CommandLineRunner {

    @Autowired
    RestTemplate restTemplate;

    @Value("${url.cheapshark:https://www.cheapshark.com}")
    private String cheapSharkHost;

    @Value("${endpoint.cheapshark:/api/1.0/deals?storeID=1&upperPrice=15}")
    private String endpoint;

    @Autowired
    MapperGameDtoToGameProduct mapper;

    @Autowired
    GameProductRepository gameProductRepository;


    @Override
    public void run(String... args) throws Exception {
        log.info("initial data from cheapshark api!");
        String url = cheapSharkHost.concat(endpoint);
        gameProductRepository.deleteAll();
        var response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GameDto>>() {});
        if(response.getStatusCode().compareTo(HttpStatus.OK)==0){
            List<GameDto> gameDtoList = response.getBody().stream().collect(Collectors.toList());
            log.info("result game list: {}",gameDtoList);
            List<GameProduct> resultList = mapper.mapGameDtoToGameProduct(gameDtoList);
            log.info("mapping to entity and save : {}",resultList);
            var isSaveData = gameProductRepository.saveAll(resultList);
            log.info("saving..... {}",isSaveData);
        }
    }
}
