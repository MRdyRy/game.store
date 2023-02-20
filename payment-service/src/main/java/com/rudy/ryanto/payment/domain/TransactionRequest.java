package com.rudy.ryanto.payment.domain;

import com.rudy.ryanto.report.domain.Cart;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionRequest extends Cart {
    private String statusPayment;
}
