package com.bowmeow.bowmeow_product.controller;

import com.bowmeow.bowmeow_product.ProductServiceProto;
import com.bowmeow.bowmeow_product.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private ProductClient productClient;


//    public ProductServiceProto.ProductInfo getProductInfo(ProductServiceProto.ProductRequest) {
//        //
//
//
//        return null;
//    }

}
