package com.rudy.ryanto.game.product.domain;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameDto {
    private String internalName;
    private String title;
    private String metacriticLink;
    private String dealID;
    private String storeID;
    private String gameID;
    private String salePrice;
    private String normalPrice;
    private String isOnSale;
    private String savings;
    private String metacriticScore;
    private String steamRatingText;
    private String steamRatingPercent;
    private String steamRatingCount;
    private String steamAppID;
    private String releaseDate;
    private String lastChange;
    private String dealRating;
    private String thumb;
}
