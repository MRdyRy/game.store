package com.rudy.ryanto.game.product.domain;

import com.rudy.ryanto.user.management.domain.AuditTrail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "GAME_PRODUCT")
public class GameProduct extends AuditTrail {
    @Id
    @Column
    private String gameId;
    @Column
    private String title;
    @Column
    private double price;
    @Column
    private String steamRatingText;
    @Column
    private String steamRatingPercent;
    @Column
    private String steamRatingCount;
    @Column
    private String thumb;
    @Column
    private String storeId;
}
