package com.rudy.ryanto.customer.login.domain;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String customerName;
    private String dob;
    private String sex;
    private String password;
    @Builder.Default
    private Boolean statusActive=true;

}
