package com.icommerceapis.restapis.repository;

import com.icommerceapis.restapis.entity.OrderShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderShoppingCartRepository extends JpaRepository<OrderShoppingCartEntity, Long> {

    Optional<OrderShoppingCartEntity> findAllByBrandIdAndSkuAndUserId(@Param("brandId") Integer brandId, @Param("sku") String sku, @Param("userId") Integer userId);
}
