package com.bowmeow.bowmeow_product.repository;

import com.bowmeow.bowmeow_product.domain.ProductInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long> {
}
