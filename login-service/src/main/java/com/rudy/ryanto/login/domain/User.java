package com.rudy.ryanto.login.domain;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String idUser;
    private String username;
    private String email;
}
