package com.rudy.ryanto.customer.login.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommonResponse {
    private String statusCode;
    private String desc;
    private CustomerDto data;
}
