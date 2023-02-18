package com.rudy.ryanto.report.domain;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private String gameId;
    private String title;
    private double price;
    private String steamRatingText;
    private String steamRatingPercent;
    private String steamRatingCount;
    private String thumb;
    private String storeId;
}
