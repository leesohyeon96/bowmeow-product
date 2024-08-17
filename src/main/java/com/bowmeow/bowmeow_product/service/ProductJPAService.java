package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.domain.ProductInfo;
import com.bowmeow.bowmeow_product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductJPAService {

    private final ProductRepository productRepository;

    /**
     * 상품 전체 목록 조회
     * @return 상품 목록
     */
    public List<ProductInfo> getProducts() {
        return productRepository.findAll();
    }

    /**
     * 상품 상세 조회
     * @return 상품 상세 정보
     */
    public ProductInfo getProduct(Integer productId) {
        return productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}