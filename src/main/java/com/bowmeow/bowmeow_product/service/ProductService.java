package com.bowmeow.bowmeow_product.service;

import com.bowmeow.bowmeow_product.domain.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductGrpcService productGrpcService;
    private final ProductJPAService productJPAService;

    /**
     * 상품 전체 목록 조회
     * @return 상품 목록
     */
    public List<ProductInfo> getProducts() {
        return productJPAService.getProducts();
    }

    /**
     * 상품 상세 조회
     * @return 상품 상세 정보
     */
    public ProductInfo getProduct(Integer productId) {
        return productJPAService.getProduct(productId);
    }
}
