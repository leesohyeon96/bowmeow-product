package com.bowmeow.bowmeow_product.repository;

import com.bowmeow.bowmeow_product.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
