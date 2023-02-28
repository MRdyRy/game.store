package com.rudy.ryanto.game.product.repository;

import com.rudy.ryanto.game.product.entity.GameProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameProductRepository extends JpaRepository<GameProduct,String> {
}
