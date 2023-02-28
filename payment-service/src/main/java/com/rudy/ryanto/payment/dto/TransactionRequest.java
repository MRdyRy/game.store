package com.rudy.ryanto.payment.dto;


import com.rudy.ryanto.payment.dto.Cart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@ToString
public class TransactionRequest extends Cart {
    private String statusPayment;
}
