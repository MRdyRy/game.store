package com.rudy.ryanto.payment.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentStatus {
    private Long transactionId;
    private String statusPayment;
}
