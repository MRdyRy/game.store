package com.rudy.ryanto.user.management.domain;

import lombok.*;

import java.util.Date;

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
