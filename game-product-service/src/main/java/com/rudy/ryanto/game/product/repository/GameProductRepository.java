package com.rudy.ryanto.game.product.repository;

import com.rudy.ryanto.game.product.domain.GameProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GameProductRepository extends JpaRepository<GameProduct,Long> {
}
