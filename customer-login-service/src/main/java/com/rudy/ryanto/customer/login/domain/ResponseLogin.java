package com.rudy.ryanto.customer.login.domain;

import com.rudy.ryanto.user.management.domain.CustomerDto;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseLogin {
    private String statusCode;
    private String desc;
    private CustomerDto data;
}