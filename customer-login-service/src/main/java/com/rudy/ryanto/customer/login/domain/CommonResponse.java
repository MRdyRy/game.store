package com.rudy.ryanto.customer.login.domain;

import com.rudy.ryanto.user.management.dto.CustomerDto;
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
