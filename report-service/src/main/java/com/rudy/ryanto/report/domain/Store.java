package com.rudy.ryanto.report.domain;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private Long idStore;
    private String storeName;
    private Boolean statusActive;
}
