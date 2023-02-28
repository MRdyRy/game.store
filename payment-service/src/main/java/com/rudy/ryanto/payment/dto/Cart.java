package com.rudy.ryanto.payment.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private Long idCart;
    private Date datePurchase;
    private List<Game> gameList;
    private BigDecimal grandTotal;
    private String statusCart;
}
