package com.rudy.ryanto.report.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long idCart;
    private Date datePurchase;
    private List<Game> gameList;
    private BigDecimal grandTotal;
    private String statusCart;
}
