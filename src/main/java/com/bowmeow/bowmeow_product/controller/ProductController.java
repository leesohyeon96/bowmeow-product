package com.bowmeow.bowmeow_product.controller;

import com.bowmeow.bowmeow_product.client.ProductClient;
import com.bowmeow.bowmeow_product.domain.ProductInfo;
import com.bowmeow.bowmeow_product.dto.ProductInfoDto;
import com.bowmeow.bowmeow_product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Product Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ModelMapper modelMapper;
    private ProductClient productClient;
    private final ProductService productService;

    /**
     * 상품 전체 목록 조회
     * @return 상품 목록
     */
    @GetMapping("")
    public List<ProductInfoDto> getProducts() {
        // 상품에 대한 목록들 조회
        List<ProductInfo> productInfos = productService.getProducts();
        return modelMapper.map(productInfos, new TypeToken<List<ProductInfoDto>>() {}.getType());
    }


    /**
     * 상품 상세 조회
     * @return 상품 상세 정보
     */
    @GetMapping("/{productId}")
    public ProductInfoDto getProduct(@PathVariable Integer productId) {
        ProductInfo productInfo = productService.getProduct(productId);
        return modelMapper.map(productInfo, ProductInfoDto.class);
    }
}
